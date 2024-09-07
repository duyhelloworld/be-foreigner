package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation;

import java.util.List;

import lombok.Data;
import vn.edu.huce.beforeigner.entities.learn.WordType;

@Data
public class CreateWordDto {
    
    private String value;

    private String mean;

    private String phonetic;

    private String audioFile;

    private WordType wordType;

    private List<String> examples;

    private List<Integer> categoryIds;
}
