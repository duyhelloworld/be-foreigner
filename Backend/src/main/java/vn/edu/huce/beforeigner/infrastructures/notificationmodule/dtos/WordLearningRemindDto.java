package vn.edu.huce.beforeigner.infrastructures.notificationmodule.dtos;

import java.util.Map;

import lombok.Data;

@Data
public class WordLearningRemindDto {
    
    private Integer wordId;

    private String value;

    private String mean;

    private Map<String, String> examples;
}
