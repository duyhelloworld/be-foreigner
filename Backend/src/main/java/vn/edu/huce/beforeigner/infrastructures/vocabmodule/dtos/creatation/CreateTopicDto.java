package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateTopicDto {
    
    private String name;

    private String description;

    private MultipartFile coverImage;

    private List<Integer> wordIds;
}
