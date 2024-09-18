package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import lombok.Builder;

@Builder
public class AnswerDto {

    public Integer id;
    
    public Integer matchId;
    
    public Boolean isTrue;

    public String correctString;
}
