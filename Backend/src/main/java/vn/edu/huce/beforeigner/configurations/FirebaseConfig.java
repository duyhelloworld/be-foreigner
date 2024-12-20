package vn.edu.huce.beforeigner.configurations;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Bean
    FirebaseApp firebaseApp() {

        GoogleCredentials credentials = null;
        try {
            var classPath = new ClassPathResource("firebase-service-account.json");
            credentials = GoogleCredentials.fromStream(classPath.getInputStream());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();
            return FirebaseApp.initializeApp(options, "be-foreigner");
        } catch (IOException e) {
            // Not file key exist / Docker env config
            log.error("Error when load firebase : {}", e.getMessage());
            return null;
        }
    }

    @Bean
    FirebaseMessaging firebaseMessaging() {
        return FirebaseMessaging.getInstance(firebaseApp());
    }
}
