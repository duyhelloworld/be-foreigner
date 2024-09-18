package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.vocab.WordType;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;

@Data
@Builder
public class WordDetailDto {
    
    private Integer id;

    private String value;

    private String mean;

    private String phonetic;

    private String audio;

    private String image;

    private WordType wordType;
    
    private Set<ExampleDetailDto> examples;

    private Set<TopicDto> topic;
}
