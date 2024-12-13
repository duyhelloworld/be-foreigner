package vn.edu.huce.beforeigner.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Getter
@Configuration
@PropertySource("classpath:secrets.properties")
public class SecretKeyProperties {

    // @Value("${unflash.access-key}")
    // private String unflashApiKey;

    @Value("${cloudinary.url}")
    private String cloudinaryUrl;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    // @Value("${spring.ai.openai.api-key}")
    // private String openAiApiKey;

}