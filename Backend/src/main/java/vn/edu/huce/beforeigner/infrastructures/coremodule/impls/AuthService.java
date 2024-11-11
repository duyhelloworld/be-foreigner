package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ICloudFileService;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {

    private final UserRepository userRepo;

    private final UserTokenRepository userTokenRepo;

    private final IUserTokenService userTokenService;

    private final IJwtService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final ICloudFileService imageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public AuthDto signIn(SignInDto signInDto) {
        User user = userRepo.findByUsername(signInDto.getUsername())
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        UserToken userToken = userTokenRepo.findByUserIdAndType(user.getId(), TokenType.REFRESH)
                .orElseThrow(() -> new AppException(ResponseCode.REFRESH_TOKEN_NOT_FOUND));
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(userToken.getToken())
                .build();
    }

    @Override
    @Transactional
    public AuthDto signUp(SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            throw new AppException(ResponseCode.USERNAME_EXISTED);
        }
        User user = new User(signUpDto.getUsername(), signUpDto.getFullname(), signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword()));
        userRepo.save(user);
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities()));
        CloudFile cloudFile = imageService.save(signUpDto.getAvatar(), CloudFileType.USER_AVATAR);
        user.setAvatar(cloudFile);
        String refreshToken = userTokenService.addNew(user, TokenType.REFRESH, null);
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void signOut(User user) {
        userTokenService.expired(user);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
