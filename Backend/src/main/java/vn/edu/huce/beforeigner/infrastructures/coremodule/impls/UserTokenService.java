package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.ITokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;

@Service
@RequiredArgsConstructor
public class UserTokenService implements IUserTokenService {

    private final UserTokenRepository userTokenRepo;

    private final ITokenService tokenService;

    @Override
    public boolean isValid(String token) {
        Optional<UserToken> userToken = userTokenRepo.findByToken(token);
        return userToken.isPresent() && userToken.get().isDisabled();
    }

    @Override
    public void addNew(String token, User user) {
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setOwner(user.getUsername());
        userTokenRepo.save(userToken);
    }

    @Override
    public void expired(User user) {
        UserToken userToken = userTokenRepo.findByUsernameAndType(user.getUsername(), TokenType.REFRESH).orElseThrow(() ->
            new AppException(ResponseCode.UNAUTHORIZED));
        userToken.setDisabled(true);
        userTokenRepo.save(userToken);
    }

    @Override
    public String renewAccess(User user, String refreshToken) {
        UserToken userToken = userTokenRepo.findByUsernameAndType(user.getUsername(), TokenType.REFRESH)
            .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));

        if (userToken.isDisabled()) {
            throw new AppException(ResponseCode.REFRESH_TOKEN_EXPIRED);
        }
        return tokenService.buildToken(user);
    }

}
