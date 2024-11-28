package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.MessagingException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;

import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IEmailService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {

    private final UserRepository userRepo;

    private final UserTokenRepository userTokenRepo;

    private final IUserTokenService userTokenService;

    private final IJwtService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final ICloudFileService cloudFileService;

    private final IEmailService emailService;

    @Value("${application.mail.admin-mail}")
    private String adminMail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public AuthDto signIn(SignInDto signInDto) {
        User user = userRepo.findByUsername(signInDto.getUsername())
                .orElseThrow(() -> new AppException(ResponseCode.USERNAME_NOT_FOUND));
        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        var userToken = userTokenRepo.findByLastModifiedByAndType(user.getUsername(), TokenType.REFRESH);
        String refreshToken = userToken.isPresent() 
                ? userToken.get().getToken()
                : userTokenService.addNew(TokenType.REFRESH, userTokenService.generateRefreshToken());
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public AuthDto signUp(SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            throw new AppException(ResponseCode.USERNAME_EXISTED);
        }
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setFullname(signUpDto.getFullname());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        var response = cloudFileService.save(signUpDto.getAvatar(), CloudFileType.USER_AVATAR);
        user.setAvatarUrl(response.getUrl());
        user.setAvatarPublicId(response.getPublicId());
        user.setAvatarFilename(response.getFilename());
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities()));
        userRepo.save(user);
        String refreshToken = userTokenService.addNew(TokenType.REFRESH, userTokenService.generateRefreshToken());
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void signOut(User user, String token) {
        userTokenService.disable(user, token, TokenType.REFRESH);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public void changePassword(User current, ChangePasswordDto changePasswordDto) {
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(),
                current.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        current.setPassword(
                passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepo.save(current);
    }

    @Override
    public String forgotPasswordRequest(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        user.setTempCode(generateCode());
        try {
            emailService.send(user.getEmail(), adminMail, "Cập nhật mật khẩu ứng dụng",
                    "<p>Code cập nhật mật khẩu của bạn là:</p>" +
                            "<h1 style='color:blue; font-size:24px; text-align:center'>" + user.getTempCode()
                            + "</h1>");
        } catch (MessagingException e) {
            throw new AppException(ResponseCode.UNEXPECTED_ERROR);
        }
        userRepo.save(user);
        return user.getTempCode();
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User user = userRepo.findByUsername(forgotPasswordDto.getUsername())
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        if (!user.getTempCode().equals(forgotPasswordDto.getCode())) {
            throw new AppException(ResponseCode.WRONG_RESET_PASSWORD_CODE);
        }
        if (passwordEncoder.matches(forgotPasswordDto.getNewPassword(), user.getPassword())) {
            throw new AppException(ResponseCode.NEW_PASS_IS_SAME_WITH_OLD);
        }
        user.setTempCode(null);
        user.setPassword(passwordEncoder.encode(forgotPasswordDto.getNewPassword()));
        userRepo.save(user);
    }

    private String generateCode() {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 100000 đến 999999
        int randomNumber = 100000 + random.nextInt(900000);
        return String.valueOf(randomNumber);
    }
}
