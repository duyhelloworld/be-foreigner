package vn.edu.huce.beforeigner.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.filter.SecurityFilter;
import vn.edu.huce.beforeigner.handler.Code401ExceptionHandler;
import vn.edu.huce.beforeigner.handler.Code403ExceptionHandler;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    private final Code401ExceptionHandler code401ExceptionHandler;
    
    private final Code403ExceptionHandler code403ExceptionHandler;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .cors(c -> c.disable())
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(r -> r.anyRequest().permitAll())
                .formLogin(f -> f.disable())
                .exceptionHandling(t -> t
                    .accessDeniedHandler(code403ExceptionHandler)
                    .authenticationEntryPoint(code401ExceptionHandler))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
