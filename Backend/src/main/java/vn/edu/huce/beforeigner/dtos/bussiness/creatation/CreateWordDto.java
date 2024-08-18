package vn.edu.huce.beforeigner.dtos.bussiness.creatation;

import java.util.Map;
import java.util.Set;

import lombok.Data;
import vn.edu.huce.beforeigner.entities.learn.WordType;

@Data
public class CreateWordDto {
    
    private String value;

    private String pronunciation;

    private String audioFile;

    private WordType wordType;

    private Map<String, String> wordExamples;

    private Set<Integer> categoryIds;
}
