package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;

public interface IUserTokenService {

    boolean isValid(String token);

    String addNew(User user, TokenType type, String token);

    void expired(User user);

    String renewAccess(User user, String refreshToken);

    String generateRefreshToken();
}