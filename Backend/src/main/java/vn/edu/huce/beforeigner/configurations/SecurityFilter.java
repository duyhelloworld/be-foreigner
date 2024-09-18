package vn.edu.huce.beforeigner.configurations;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IInvalidTokenService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.ITokenService;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    
    private IInvalidTokenService invalidTokenService;

    private ITokenService tokenService;

    private UserDetailsService appUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = tokenService.getToken(request);

        if (token != null && tokenService.isValidToken(token)) {
            if (invalidTokenService.isExisted(token)) {
                throw new AppException(ResponseCode.TOKEN_EXPIRED);
            }
            UserDetails user = appUserDetailService.loadUserByUsername(tokenService.extractUsername(token));
            var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
    
}
