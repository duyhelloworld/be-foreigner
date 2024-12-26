package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ChangePasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.ForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestForgotPasswordDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.RequestVerifyEmailDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.VerifyEmailDto;

public interface IAuthService {
    
    /**
     * Đăng nhập
     * @param signInDto
     * @return
     */
    AuthDto signIn(SignInDto signInDto);

    /**
     * Đăng kí
     * @param signUpDto
     * @return
     */
    AuthDto signUp(SignUpDto signUpDto);

    /**
     * Đăng xuất
     * @param user
     * @param token
     */
    void signOut(Account user, String token);

    /**
     * Đổi mật khẩu
     * @param user
     * @param changePasswordDto
     */
    void changePassword(Account user, ChangePasswordDto changePasswordDto);
    
    /**
     * Yêu cầu xác thực tài khoản chưa
     * @param user
     * @param requestSignupDto
     */
    void requestVerifyAccount(Account user, RequestVerifyEmailDto requestSignupDto);

    /**
     * 
     * @param user
     * @param verifyEmailDto
     */
    void verifyEmail(Account user, VerifyEmailDto verifyEmailDto);

    void requestForgotPassword(RequestForgotPasswordDto requestForgotPasswordDto);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);
}
