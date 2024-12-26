package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail;

import java.util.List;
import java.util.Map;

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

    private List<Map<String, String>> examples;
}
