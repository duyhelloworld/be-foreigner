package vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.huce.beforeigner.domains.core.User;

public interface ITokenService {
    
    String buildToken(User user);

    String getToken(HttpServletRequest request);

    boolean isValidToken(String token);

    String extractUsername(String token);
}
