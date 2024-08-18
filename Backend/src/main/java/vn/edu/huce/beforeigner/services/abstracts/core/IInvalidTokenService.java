package vn.edu.huce.beforeigner.services.abstracts.core;

import vn.edu.huce.beforeigner.entities.core.User;

public interface IInvalidTokenService {

    boolean isExisted(String token);

    void addNew(String token, User user);
}