package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers;

import lombok.Builder;

@Builder
public class AnswerOptionDto {
    
    public String value;

    public String image;

    public String audio;

    public Boolean isTrue;
    
}
