package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.entities.learn.WordType;

@Data
@Builder
public class WordDetailDto {

    private Integer id;

    private String value;
    
    private String audioFile;

    private WordType wordType;

    private String phonetic;

    private Map<String, String> meanAndExamples;
}
