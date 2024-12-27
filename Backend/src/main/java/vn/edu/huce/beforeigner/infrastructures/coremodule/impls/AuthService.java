package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import jakarta.mail.MessagingException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.constants.UserConstants;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.AccountSetting;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.SubscriptionPlan;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.core.repo.AccountRepo;
import vn.edu.huce.beforeigner.domains.core.repo.AccountTokenRepo;
import vn.edu.huce.beforeigner.domains.core.repo.AccountSettingRepo;
import vn.edu.huce.beforeigner.domains.remind.NotificationMethod;
import vn.edu.huce.beforeigner.domains.remind.SettingType;
import vn.edu.huce.beforeigner.domains.streak.Streak;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IEmailService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAccountTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestVerifyEmailDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.VerifyEmailDto;
import vn.edu.huce.beforeigner.properties.SecretKeyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {

    private final AccountRepo accountRepo;

    private final AccountTokenRepo accountTokenRepo;

    private final AccountSettingRepo accountSettingRepo;

    private final IAccountTokenService accountTokenService;

    private final IJwtService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final ICloudFileService cloudFileService;

    private final IEmailService emailService;

    private final SecretKeyProperties secretKeyProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findByUsername(username).orElse(null);
    }

    @Override
    public void requestForgotPassword(RequestForgotPasswordDto requestForgotPasswordDto) {
        Account account = accountRepo
                .findByUsernameOrEmail(requestForgotPasswordDto.getUsername(), requestForgotPasswordDto.getEmail())
                .orElseThrow(() -> new AppException(ResponseCode.USERNAME_NOT_FOUND));

        var optToken = accountTokenRepo.findValidTokenByTypeAndOwner(TokenType.RESET_PASSWORD, account.getUsername());
        if (optToken.isPresent()) {
            // Đang có 1 phiên reset mật khẩu
            return;
        }
        var accountToken = optToken.get();
        accountToken.setToken(generateCode());
        accountToken.setType(TokenType.RESET_PASSWORD);
        try {
            emailService.send(account.getEmail(), secretKeyProperties.getMailUsername(), "Yêu cầu đặt lại mật khẩu",
                    "<p>Mã đặt lại mật khẩu của bạn là:</p>" +
                            "<h1 style='color:blue; font-size:24px; text-align:center'>" + accountToken.getToken()
                            + "</h1>");
            accountTokenRepo.save(accountToken);
        } catch (MessagingException e) {
            throw new AppException(ResponseCode.EMAIL_ADDRESS_MAY_NOT_EXIST);
        }
    }

    @Override
    public void requestVerifyAccount(Account account, RequestVerifyEmailDto requestVerifyEmailDto) {
        if (account.isVerified()) {
            return;
        }
        var optToken = accountTokenRepo.findValidTokenByTypeAndOwner(TokenType.RESET_PASSWORD, account.getUsername());
        if (optToken.isPresent()) {
            // Đang có 1 phiên xác thực tài khoản
            return;
        }
        var accountToken = optToken.get();
        accountToken.setToken(generateCode());
        accountToken.setType(TokenType.RESET_PASSWORD);
        try {
            String targetMail = Optional.ofNullable(requestVerifyEmailDto)
                    .map(r -> r.getEmail())
                    .orElse(account.getEmail());
            emailService.send(targetMail, secretKeyProperties.getMailUsername(), "Xác thực email",
                    "<p>Mã xác thực email của bạn là:</p>" +
                            "<h1 style='co lor:blue; font-size:24px; text-align:center'>"
                            + accountToken.getToken() + "</h1>");
            accountTokenRepo.save(accountToken);
        } catch (MessagingException e) {
            throw new AppException(ResponseCode.EMAIL_ADDRESS_MAY_NOT_EXIST);
        }
    }

    @Override
    public void verifyEmail(Account account, VerifyEmailDto verifyEmailDto) {
        if (account.isVerified()) {
            return;
        }
        var accountToken = accountTokenRepo.findValidTokenByTypeAndOwner(TokenType.VERIFY_EMAIL, account.getUsername())
            .orElseThrow(() -> new AppException(ResponseCode.NO_VERIFY_REQUEST_CREATED));
        if (accountToken.getToken().equals(verifyEmailDto.getCode())) {
            accountToken.setExpiredAt(LocalDateTime.now());
            account.setVerified(true);
            accountRepo.save(account);
        }
    }

    @Override
    public AuthDto signIn(SignInDto signInDto) {
        Account account = accountRepo.findByUsername(signInDto.getUsername())
        .orElseThrow(() -> new AppException(ResponseCode.USERNAME_NOT_FOUND));
        if (!passwordEncoder.matches(signInDto.getPassword(), account.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        var accountToken = accountTokenRepo.findValidTokenByTypeAndOwner(TokenType.REFRESH, account.getUsername());
        String refreshToken = accountToken.isPresent()
                ? accountToken.get().getToken()
                : accountTokenService.addNew(TokenType.REFRESH, accountTokenService.generateRefreshToken());

        return AuthDto.builder()
                .accessToken(tokenService.buildToken(account))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public AuthDto signUp(SignUpDto signUpDto) {
        if (accountRepo.existsByUsername(signUpDto.getUsername())) {
            throw new AppException(ResponseCode.USERNAME_EXISTED);
        }
        Account user = new Account();
        user.setUsername(signUpDto.getUsername());
        user.setFullname(signUpDto.getFullname());
        user.setEmail(signUpDto.getEmail());
        user.setLevel(signUpDto.getLevel());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setVerified(false);
        user.setQuota(UserConstants.QUOTA_PER_DAY);
        user.setPlan(SubscriptionPlan.FREE);
        user.setRole(Role.USER);

        var response = cloudFileService.save(signUpDto.getAvatar(), CloudFileType.USER_AVATAR);
        user.setAvatarUrl(response.getUrl());
        user.setAvatarPublicId(response.getPublicId());

        accountRepo.save(user);
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities()));
        String refreshToken = accountTokenService.addNew(TokenType.REFRESH, accountTokenService.generateRefreshToken());
        
        AccountSetting remindByNoti = new AccountSetting();
        remindByNoti.setSettingType(SettingType.LEARN_REMIND);
        remindByNoti.setEnabled(true);
        remindByNoti.setRemindMethod(NotificationMethod.NOTIFICATION);

        AccountSetting remindByMail = new AccountSetting();
        remindByMail.setSettingType(SettingType.LEARN_REMIND);
        remindByMail.setEnabled(true);
        remindByMail.setRemindMethod(NotificationMethod.EMAIL);
        accountSettingRepo.saveAll(List.of(remindByMail, remindByNoti));

        Streak streak = new Streak();
        streak.setPlusStreak(false);
        streak.setHighestStreak(0);
        streak.setCurrentStreak(0);
        
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void signOut(Account user, String token) {
        accountTokenService.expire(user, token, TokenType.REFRESH);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public void changePassword(Account account, ChangePasswordDto changePasswordDto) {
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(),
                account.getPassword())) {
            throw new AppException(ResponseCode.USERNAME_OR_PASSWORD_INCORRECT);
        }
        var accountToken = accountTokenRepo.findValidTokenByTypeAndOwner(TokenType.VERIFY_EMAIL, account.getUsername())
            .orElseThrow(() -> new AppException(ResponseCode.NO_VERIFY_REQUEST_CREATED));
        if (accountToken.getToken().equals(changePasswordDto.getCode())) {
            accountToken.setExpiredAt(LocalDateTime.now());
            accountTokenRepo.save(accountToken);
            account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            accountRepo.save(account);
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        // Account account = accountRepo.findByUsername(forgotPasswordDto.getUsername())
        //         .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        // if (!account.getToken().equals(forgotPasswordDto.getCode())) {
        //     throw new AppException(ResponseCode.WRONG_RESET_PASSWORD_CODE);
        // }
        // if (passwordEncoder.matches(forgotPasswordDto.getNewPassword(), user.getPassword())) {
        //     throw new AppException(ResponseCode.NEW_PASS_IS_SAME_WITH_OLD);
        // }
        // user.setToken(null);
        // user.setTokenType(null);
        // user.setPassword(passwordEncoder.encode(forgotPasswordDto.getNewPassword()));
        // accountRepo.save(user);
    }

    private String generateCode() {
        Random random = new Random();
        // Tạo số ngẫu nhiên từ 100000 đến 999999
        int randomNumber = 100000 + random.nextInt(900000);
        return String.valueOf(randomNumber);
    }
}
