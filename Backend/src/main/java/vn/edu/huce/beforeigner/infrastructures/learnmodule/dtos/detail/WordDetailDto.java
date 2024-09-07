package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.entities.learn.WordType;

@Data
@Builder
public class WordDetailDto {

    private Integer id;

    private String value;

    private String mean;
    
    private String audioFile;

    private WordType wordType;

    private String phonetic;

    private List<String> examples;
}
