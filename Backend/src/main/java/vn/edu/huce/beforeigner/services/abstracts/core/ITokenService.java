package vn.edu.huce.beforeigner.services.abstracts.core;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.huce.beforeigner.entities.core.User;

public interface ITokenService {
    
    String buildToken(User user);

    String getToken(HttpServletRequest request);

    boolean isValidToken(String token);

    String extractUsername(String token);
}
