package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.ClaimTypes;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IJwtService;
import vn.edu.huce.beforeigner.utils.DatetimeUtils;

@Slf4j
@Service
public class JwtService implements IJwtService {

    @Value("${application.auth.jwt.issuer}")
    private String issuer;

    @Value("${application.auth.jwt.expire-duration}")
    private Duration expireDuration;

    @Value("${application.auth.jwt.secret-key}")
    private String secretKey;

    @Override
    public String buildToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .claim(ClaimTypes.USERNAME.name(), user.getUsername())
                .claim(ClaimTypes.EMAIL.name(), user.getEmail())
                .claim(ClaimTypes.USERID.name(), user.getId())
                .issuer(issuer)
                .issuedAt(DatetimeUtils.getDate(now))
                .expiration(DatetimeUtils.getDate(now.plus(expireDuration)))
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.contains("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    @Override
    public boolean isValidToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            getParser().parseSignedClaims(token);
            return true;
        } catch (UnsupportedJwtException e) {
            log.error("Token type invalid : " + e.getMessage());
        } catch (JwtException e) {
            log.error("Token invalid : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Jwt is null / whitespace");
        }
        return false;
    }

    @Override
    public String extractUsername(String token) {
        return getParser().parseSignedClaims(token)
                .getPayload().get(ClaimTypes.USERNAME.name(), String.class);
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private JwtParser getParser() {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .requireIssuer(issuer)
                .build();
    }
}
