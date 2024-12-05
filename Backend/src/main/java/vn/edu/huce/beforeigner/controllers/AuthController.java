package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAuthenticated;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.exceptions.ApiResponse;
import jakarta.validation.Valid;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RenewTokenDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestVerifyEmailDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.VerifyEmailDto;

import  org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    private final IUserTokenService userTokenService;

    @PostMapping("sign-in")
    public ApiResponse<AuthDto> signIn(@Valid @RequestBody SignInDto signInDto) {
        return ApiResponse.ok(authService.signIn(signInDto));
    }

    @PostMapping("sign-up")
    public ApiResponse<AuthDto> signUp(@Valid @ModelAttribute SignUpDto signUpDto) {
        return ApiResponse.ok(authService.signUp(signUpDto));
    }

    @IsAuthenticated
    @PutMapping("sign-out")
    public ApiResponse<Void> signOut(@AuthenticationPrincipal User user, @RequestParam String token) {
        authService.signOut(user, token);
        return ApiResponse.ok();
    }

    @IsAuthenticated
    @PutMapping("renew")
    public ApiResponse<AuthDto> renew(@AuthenticationPrincipal User user, @Valid @RequestBody RenewTokenDto renewTokenDto) {
        return ApiResponse.ok(userTokenService.renewAccess(user, renewTokenDto.getRefreshToken()));
    }

    @PostMapping("forgot/request")
    public ApiResponse<Void> forgot(
        @Valid @RequestBody RequestForgotPasswordDto requestForgotPasswordDto) {
        authService.requestForgotPassword(requestForgotPasswordDto);
        return ApiResponse.ok();
    }

    @PostMapping("forgot/confirm")
    public ApiResponse<Void> verifyForgotPassword(
        @Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
        authService.forgotPassword(forgotPasswordDto);
        return ApiResponse.ok();
    }

    @IsUser
    @PostMapping("change-pass")
    public ApiResponse<Void> changePassword(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        authService.changePassword(user, changePasswordDto);
        return ApiResponse.ok();
    }

    @IsUser
    @PostMapping("verify-email/request")
    public ApiResponse<Void> requestVerifyEmail(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody(required = false) RequestVerifyEmailDto requestVerifyEmailDto) {
        authService.requestVerifyEmail(user, requestVerifyEmailDto);
        return ApiResponse.ok();
    }

    @IsUser
    @PostMapping("verify-email/confirm")
    public ApiResponse<Void> verifyEmail(
            @AuthenticationPrincipal User user,
            @RequestBody VerifyEmailDto verifyEmailDto) {
        authService.verifyEmail(user, verifyEmailDto);
        return ApiResponse.ok();
    }
}
