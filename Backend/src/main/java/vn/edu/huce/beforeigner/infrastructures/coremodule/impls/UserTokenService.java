package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.configurations.AuditorConfig;
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
    public boolean isValid(TokenType type, String token) {
        if (StringUtils.hasText(token)) {
            switch (type) {
                case REFRESH:
                    return token.length() == 36;
                case NOTIFICATION:
                    return token.length() > 40;
            }
        }
        return false;
    }

    @Override
    public String addNew(TokenType type, String token) {
        var optUsertoken = userTokenRepo.findByTypeAndTokenAndIsDisabledFalse(type, token);
        if (optUsertoken.isPresent()) {
            return optUsertoken.get().getToken();
        }
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setLastModifiedAt(LocalDateTime.now());
        userToken.setType(type);
        userTokenRepo.save(userToken);
        return userToken.getToken();
    }

    @Override
    public void disable(User user, String token, TokenType type) {
        userTokenRepo.disable(AuditorConfig.getAuditor(user), type, token);
    }

    @Override
    public AuthDto renewAccess(User user, String refreshToken) {
        UserToken userToken = userTokenRepo.findByLastModifiedByAndType(AuditorConfig.getAuditor(user), TokenType.REFRESH)
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));

        if (userToken.isDisabled()) {
            throw new AppException(ResponseCode.REFRESH_TOKEN_EXPIRED);
        }
        userToken.setToken(generateRefreshToken());
        userToken.setLastModifiedAt(LocalDateTime.now());
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(user))
                .refreshToken(userToken.getToken())
                .build();
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
