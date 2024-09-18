package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAuthenticated;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAuthService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    private IAuthService authService;

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
    
}
