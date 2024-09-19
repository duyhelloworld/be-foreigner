package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignInDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.SignUpDto;

public interface IAuthService {
    
    AuthDto signIn(SignInDto signInDto);

    AuthDto signUp(SignUpDto signUpDto);

    void signOut(User current);

    String renewAccess(String refreshToken);
}
