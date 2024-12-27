package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.AccountToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.core.repo.AccountTokenRepo;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IAccountTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthDto;

@Service
@RequiredArgsConstructor
public class UserTokenService implements IAccountTokenService {

    private final AccountTokenRepo accountTokenRepo;

    private final IJwtService tokenService;

    @Override
    public boolean isValid(TokenType type, String token) {
        if (StringUtils.hasText(token)) {
            int length = token.length();
            switch (type) {
                case REFRESH:
                    return length == 36;
                case NOTIFICATION:
                    return length > 40;
                case RESET_PASSWORD:
                case VERIFY_EMAIL:
                    return length == 6;
            }
        }
        return false;
    }

    @Override
    public String addNew(TokenType type, String token) {
        var isExisted = accountTokenRepo.existsByTypeAndToken(type, token);
        if (!isExisted) {
            AccountToken userToken = new AccountToken();
            userToken.setToken(token);
            userToken.setExpiredAt(LocalDateTime.now().plusDays(10));
            userToken.setType(type);
            accountTokenRepo.save(userToken);
        }
        return token;
    }

    @Override
    public void expire(Account account, String token, TokenType type) {
        AccountToken accountToken = accountTokenRepo.findValidTokenByTypeAndOwner(type, account.getUsername())
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
        accountToken.setExpiredAt(LocalDateTime.now());
        accountTokenRepo.save(accountToken);
    }

    @Override
    public AuthDto renewAccess(Account account, String refreshToken) {
        AccountToken accountToken = accountTokenRepo
                .findValidTokenByTypeAndOwner(TokenType.REFRESH, account.getUsername())
                .orElseThrow(() -> new AppException(ResponseCode.REFRESH_TOKEN_EXPIRED));
        accountToken.setToken(generateRefreshToken());
        accountToken.setExpiredAt(LocalDateTime.now().plusDays(5));
        return AuthDto.builder()
                .accessToken(tokenService.buildToken(account))
                .refreshToken(accountToken.getToken())
                .build();
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
