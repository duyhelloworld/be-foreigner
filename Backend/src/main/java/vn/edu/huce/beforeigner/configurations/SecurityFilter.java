package vn.edu.huce.beforeigner.configurations;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IInvalidTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.ITokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthenticatedUser;
import vn.edu.huce.beforeigner.repositories.UserRepository;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    
    private IInvalidTokenService invalidTokenService;

    private ITokenService tokenService;

    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = tokenService.getToken(request);

        if (token != null && tokenService.isValidToken(token)) {
            if (invalidTokenService.isExisted(token)) {
                throw new AppException(ResponseCode.TOKEN_EXPIRED);
            }

            User user = userRepo.findByUsername(tokenService.extractUsername(token))
                .orElseThrow(() -> new AppException(ResponseCode.UNAUTHORIZED));
            AuthenticatedUser authenticatedUser = AuthenticatedUser.instance(user);
            var authToken = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
    
}
