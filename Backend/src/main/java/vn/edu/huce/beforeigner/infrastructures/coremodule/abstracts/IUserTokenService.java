package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;

public interface IUserTokenService {

    boolean isValid(String token);

    void addNew(String refreshToken, User user);
    
    void expired(User user);

    String renewAccess(User user, String refreshToken);
}