package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import lombok.Builder;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;

@Builder
public class AnswerDto {

    public Integer id;
    
    public Integer matchId;
    
    public Boolean isTrue;

    public WordDto wordDto;
}
