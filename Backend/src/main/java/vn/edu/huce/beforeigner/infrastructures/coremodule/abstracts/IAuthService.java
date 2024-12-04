package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestVerifyEmailDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.VerifyEmailDto;

public interface IAuthService {
    
    AuthDto signIn(SignInDto signInDto);

    AuthDto signUp(SignUpDto signUpDto);

    void signOut(User current, String token);

    void changePassword(User current, ChangePasswordDto changePasswordDto);

    void requestVerifyEmail(User user, RequestVerifyEmailDto requestSignupDto);

    void verifyEmail(User user, VerifyEmailDto verifyEmailDto);

    void requestForgotPassword(RequestForgotPasswordDto requestForgotPasswordDto);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);
}
