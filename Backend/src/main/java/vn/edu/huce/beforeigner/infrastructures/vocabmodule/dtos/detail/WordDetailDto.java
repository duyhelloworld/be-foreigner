package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordDetailDto {
    
    private Integer id;

    private String value;

    private String mean;

    private String phonetic;

    private String audio;

    private String image;

    private Set<ExampleDto> examples;
}
