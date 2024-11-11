package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAuthenticated;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RenewTokenDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;

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
    public AuthDto signIn(@RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

    @PostMapping("sign-up")
    public AuthDto signUp(SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }

    @IsAuthenticated
    @PutMapping("sign-out")
    public void signOut(@AuthenticationPrincipal User user) {
        authService.signOut(user);
    }

    @PutMapping("renew")
    public AuthDto renew(@AuthenticationPrincipal User user, @RequestBody RenewTokenDto renewTokenDto) {
        return userTokenService.renewAccess(user, renewTokenDto.getRefreshToken());
    }
    
}
