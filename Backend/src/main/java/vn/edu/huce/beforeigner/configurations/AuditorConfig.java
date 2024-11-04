package vn.edu.huce.beforeigner.configurations;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.User;

@Slf4j
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorConfig implements AuditorAware<String> {

    @Bean
    AuditorAware<String> auditorAware() {
        return new AuditorConfig();
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        User user = getUser();
        return user == null ? Optional.empty() : Optional.of(user.getId());
    }

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        try {
            return (User) authentication.getPrincipal();
        } catch (ClassCastException e) {
            log.error("Error when cast " + authentication.getPrincipal() + " to AuthenticatedUser", e);
            return null;
        }    
    }
}