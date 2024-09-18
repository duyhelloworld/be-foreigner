package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.InvalidToken;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.InvalidTokenRepository;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IInvalidTokenService;

@Service
@AllArgsConstructor
public class InvalidTokenService implements IInvalidTokenService {

    private InvalidTokenRepository invalidTokenRepo;

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
