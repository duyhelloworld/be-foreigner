package vn.edu.huce.beforeigner.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebClientConfig {

    @Bean
    RestTemplate restTemplate() {
        var template = new RestTemplate();
        return template;
    }
}
