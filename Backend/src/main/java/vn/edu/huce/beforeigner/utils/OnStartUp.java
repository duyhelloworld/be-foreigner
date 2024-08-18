package vn.edu.huce.beforeigner.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.entities.core.Role;
import vn.edu.huce.beforeigner.entities.core.TokenProvider;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.repositories.UserRepo;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    
    @Value("${application.auth.test-user.username}")
    private String testUsername;

    @Value("${application.auth.test-user.password}")
    private String testPassword;

    @Value("${application.auth.test-user.email}")
    private String testEmail;

    @Value("${application.auth.test-user.fullname}")
    private String testFullname;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() < 1) {
            String encodedPass = passwordEncoder.encode(testPassword);
            User admin = new User(testUsername, testFullname, testEmail, encodedPass, TokenProvider.LOCAL, Role.ADMIN);
            userRepo.save(admin);
            log.info("Saved admin account!");
        }
    }
}
