package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;

@Service
@RequiredArgsConstructor
public class UserTokenService implements IUserTokenService {

    private final UserTokenRepository userTokenRepo;

    private final IJwtService tokenService;

    @Override
    public boolean isValidRefreshToken(String token) {
        UserToken userToken = userTokenRepo.findByToken(token)
                .orElse(null);
        return userToken != null && !userToken.isDisabled();
    }

    @Override
    public String addNew(User user, TokenType type, String token) {
        UserToken userToken = new UserToken();
        if (type == TokenType.REFRESH) {
            userToken.setToken(generateRefreshToken());
        } else {
            userToken.setToken(token);
        }
        userToken.setUserId(user.getId());
        userToken.setType(type);
        userTokenRepo.save(userToken);
        return userToken.getToken();
    }

    @Override
    public void expired(User user) {
        UserToken userToken = userTokenRepo.findByUserIdAndType(user.getId(), TokenType.REFRESH)
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        userTokenRepo.delete(userToken);
    }

    @Override
    public AuthDto renewAccess(User user, String refreshToken) {
        UserToken userToken = userTokenRepo.findByUserIdAndType(user.getId(), TokenType.REFRESH)
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));

        if (userToken.isDisabled()) {
            throw new AppException(ResponseCode.REFRESH_TOKEN_EXPIRED);
        }
        userToken.setToken(generateRefreshToken());
        userToken.setCreatedAt(LocalDateTime.now());
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(userToken.getToken())
                .build();
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
