package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.vocab.WordType;

@Data
@Valid
public class CreateWordDto {
    
    private String value;
    
    private String phonetic;

    private MultipartFile image;
    
    private WordType wordType;

    private List<CreateExampleDto> examples;
}
