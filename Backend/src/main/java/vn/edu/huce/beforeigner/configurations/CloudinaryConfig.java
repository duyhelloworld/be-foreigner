package vn.edu.huce.beforeigner.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.properties.SecretKeyProperties;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfig {
    
    private final SecretKeyProperties secretKeyProperties;

    @Bean
	Cloudinary cloudinary() {
		Cloudinary cloudinary = new Cloudinary(secretKeyProperties.getCloudinaryUrl());
		return cloudinary;
	}
}
