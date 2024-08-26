package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation;

import java.util.List;
import java.util.Map;

import lombok.Data;
import vn.edu.huce.beforeigner.entities.learn.WordType;

@Data
public class CreateWordDto {
    
    private String value;

    private String phonetic;

    private String audioFile;

    private WordType wordType;

    private Map<String, String> wordExamples;

    private List<Integer> categoryIds;
}
