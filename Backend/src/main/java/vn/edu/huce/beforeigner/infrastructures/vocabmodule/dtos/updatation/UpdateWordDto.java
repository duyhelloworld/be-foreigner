package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UpdateWordDto {
    
    private String mean;

    private String value;

    private String phonetic;

    private MultipartFile audio;

    private MultipartFile image;

    private List<Integer> sentenseIds;
}
