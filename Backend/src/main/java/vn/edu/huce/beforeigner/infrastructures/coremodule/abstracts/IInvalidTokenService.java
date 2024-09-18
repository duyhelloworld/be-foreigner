package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;

public interface IInvalidTokenService {

    boolean isExisted(String token);

    void addNew(String token, User user);
}