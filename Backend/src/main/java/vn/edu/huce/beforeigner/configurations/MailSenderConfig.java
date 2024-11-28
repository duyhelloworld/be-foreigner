package vn.edu.huce.beforeigner.configurations;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.properties.SecretKeyProperties;

@Configuration
@RequiredArgsConstructor
public class MailSenderConfig {
    
    private final SecretKeyProperties secretKeyProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        
        // Cấu hình SMTP Server
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(secretKeyProperties.getMailUsername());
        mailSender.setPassword(secretKeyProperties.getMailPassword());
        mailSender.setDefaultEncoding("UTF-8");
        // Các thuộc tính bổ sung
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }
}
