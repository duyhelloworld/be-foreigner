package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.util.UUID;

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
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.ITokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ImageType;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.UploadResponse;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {

    private final UserRepository userRepo;

    private final UserTokenRepository userTokenRepo;

    private final IUserTokenService userTokenService;

    private final ITokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final IImageService imageService;

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
        UserToken userToken = userTokenRepo.findByUsernameAndType(user.getUsername(), TokenType.REFRESH)
            .orElseThrow(() -> new AppException(ResponseCode.REFRESH_TOKEN_NOT_FOUND));
        return AuthDto.builder()
                .access(tokenService.buildToken(user))
                .refresh(userToken.getToken())
                .build();
    }

    @Override
    @Transactional
    public AuthDto signUp(SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            throw new AppException(ResponseCode.USERNAME_EXISTED);
        }
        User user = new User(signUpDto.getUsername(), signUpDto.getFullname(), signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword()));
        
        UploadResponse response = imageService.save(signUpDto.getAvatar(), ImageType.USER_AVATAR);
        user.setAvatar(response.getFileUrl());
        user.setPublicId(response.getPublicId());
        user.setLevel(signUpDto.getLevel());

        var refreshToken = new UserToken();
        refreshToken.setToken(generateRefreshToken());
        refreshToken.setOwner(user.getUsername());
        refreshToken.setType(TokenType.REFRESH);
        userTokenRepo.save(refreshToken);

        userRepo.save(user);
        return AuthDto.builder()
                .access(tokenService.buildToken(user))
                .refresh(refreshToken.getToken())
                .build();
    }

    @Override
    public void signOut(User user) {
        userTokenService.expired(user);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
