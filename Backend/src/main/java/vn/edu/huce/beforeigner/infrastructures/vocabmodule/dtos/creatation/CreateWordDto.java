package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import vn.edu.huce.beforeigner.domains.vocab.WordType;

@Data
public class CreateWordDto {
    
    private String value;
    
    private String mean;
    
    private String phonetic;

    private MultipartFile image;
    
    private MultipartFile audio;
    
    private WordType wordType;

    private List<CreateExampleDto> createExamples;
    
    private List<Integer> topicIds;
}
