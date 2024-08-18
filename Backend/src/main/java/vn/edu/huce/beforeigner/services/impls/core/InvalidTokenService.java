package vn.edu.huce.beforeigner.services.impls.core;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.core.InvalidToken;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.repositories.InvalidTokenRepo;
import vn.edu.huce.beforeigner.services.abstracts.core.IInvalidTokenService;

@Service
@AllArgsConstructor
public class InvalidTokenService implements IInvalidTokenService {

    private InvalidTokenRepo invalidTokenRepo;

    @Override
    public boolean isExisted(String token) {
        return invalidTokenRepo.existsByToken(token);
    }

    @Override
    public void addNew(String token, User user) {
        InvalidToken invalidToken = new InvalidToken();
        invalidToken.setToken(token);
        invalidToken.setUser(user);
        invalidTokenRepo.save(invalidToken);
    }
    
}
