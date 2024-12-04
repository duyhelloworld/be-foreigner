package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.MessagingException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.constants.UserConstants;
import vn.edu.huce.beforeigner.domains.core.CodeTarget;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.SubscriptionPlan;
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
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestVerifyEmailDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.VerifyEmailDto;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

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
    public void requestForgotPassword(RequestForgotPasswordDto requestForgotPasswordDto) {
        User user = userRepo
                .findByUsernameOrEmail(requestForgotPasswordDto.getUsername(), requestForgotPasswordDto.getEmail())
                .orElseThrow(() -> new AppException(ResponseCode.USERNAME_NOT_FOUND));
        user.setTempCode(generateCode());
        user.setCodeTarget(CodeTarget.RESET_PASSWORD);
        try {
            emailService.send(user.getEmail(), adminMail, "Yêu cầu đặt lại mật khẩu",
                    "<p>Mã đặt lại mật khẩu của bạn là:</p>" +
                            "<h1 style='color:blue; font-size:24px; text-align:center'>" + user.getTempCode()
                            + "</h1>");
            userRepo.save(user);
        } catch (MessagingException e) {
            throw new AppException(ResponseCode.EMAIL_ADDRESS_MAY_NOT_EXIST);
        }
    }

    @Override
    public void requestVerifyEmail(User user, RequestVerifyEmailDto requestVerifyEmailDto) {
        user.setTempCode(generateCode());
        user.setCodeTarget(CodeTarget.VERIFY_EMAIL);
        try {
            String targetMail = Optional.ofNullable(requestVerifyEmailDto).map(r -> r.getEmail())
                    .orElse(user.getEmail());
            emailService.send(targetMail, adminMail, "Xác thực email",
                    "<p>Mã xác thực email của bạn là:</p>" +
                            "<h1 style='co lor:blue; font-size:24px; text-align:center'>"
                            + user.getTempCode() + "</h1>");
            userRepo.save(user);
        } catch (MessagingException e) {
            throw new AppException(ResponseCode.EMAIL_ADDRESS_MAY_NOT_EXIST);
        }
    }

    @Override
    public void verifyEmail(User user, VerifyEmailDto verifyEmailDto) {
        if (user.getTempCode() == null) {
            throw new AppException(ResponseCode.INVALID_REQUEST);
        }
        if (user.getTempCode().equals(verifyEmailDto.getCode())) {
            user.setTempCode(null);
            user.setCodeTarget(null);
            user.setVerified(true);
            userRepo.save(user);
        }
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
        user.setLevel(signUpDto.getLevel());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setAllowMail(true);
        user.setVerified(false);
        user.setAllowNotification(false);
        user.setQuota(UserConstants.QUOTA_PER_DAY);
        user.setPlan(SubscriptionPlan.FREE);
        user.setIsFirstTry(true);
        user.setRole(Role.USER);

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
    public void changePassword(User user, ChangePasswordDto changePasswordDto) {
        if (user.getTempCode() == null || user.getCodeTarget() != CodeTarget.RESET_PASSWORD) {
            throw new AppException(ResponseCode.INVALID_REQUEST);
        }
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(),
                user.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        user.setCodeTarget(null);
        user.setTempCode(null);
        userRepo.save(user);
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
        user.setCodeTarget(null);
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
