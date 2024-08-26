package vn.edu.huce.beforeigner.configurations;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthenticatedUser;

@Slf4j
public abstract class SecurityHolder {
    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        try {
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
            return authenticatedUser.getUser();
        } catch (ClassCastException e) {
            log.error("Error when cast " + authentication.getPrincipal() + " to AuthenticatedUser", e);
            return null;
        }    
    }
}
