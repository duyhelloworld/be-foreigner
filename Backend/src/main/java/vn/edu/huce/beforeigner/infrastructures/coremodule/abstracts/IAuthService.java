package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;

public interface IAuthService {
    
    AuthDto signIn(SignInDto signInDto);

    AuthDto signUp(SignUpDto signUpDto);

    void signOut(User current, String token);

    void changePassword(User current, ChangePasswordDto changePasswordDto);

    String forgotPasswordRequest(String username);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);
}
