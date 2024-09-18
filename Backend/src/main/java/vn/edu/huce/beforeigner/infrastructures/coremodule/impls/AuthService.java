package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.TokenProvider;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.ITokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IFileService;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {

    private UserRepository userRepo;

    private ITokenService tokenService;

    private PasswordEncoder passwordEncoder;

    private IFileService fileService;

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
        return AuthDto.builder()
                .access(tokenService.buildToken(user))
                .build();
    }

    @Override
    public AuthDto signUp(SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            throw new AppException(ResponseCode.USERNAME_EXISTED);
        }
        User user = new User();
        String avatarUrl = fileService.save(signUpDto.getAvatar());
        user.setAvatar(avatarUrl);
        user.setEmail(signUpDto.getEmail());
        user.setFullname(signUpDto.getFullname());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setLevel(signUpDto.getLevel());
        user.setProvider(TokenProvider.LOCAL);
        user.setRole(Role.USER);
        user.setUsername(signUpDto.getUsername());
        userRepo.save(user);
        return AuthDto.builder()
                .access(tokenService.buildToken(user))
                .build();
    }

    @Override
    public void signOut(User current) {

    }

}
