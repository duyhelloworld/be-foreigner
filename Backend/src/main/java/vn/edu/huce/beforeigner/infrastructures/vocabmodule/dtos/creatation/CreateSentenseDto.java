package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateSentenseDto {
    
    private String value;

    private String mean;
    
    private MultipartFile image;

    private MultipartFile audio;
}
