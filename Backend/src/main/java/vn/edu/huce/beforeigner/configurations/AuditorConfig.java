package vn.edu.huce.beforeigner.configurations;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

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
        return Optional.ofNullable(SecurityHolder.getUser().getUsername());
    }
}