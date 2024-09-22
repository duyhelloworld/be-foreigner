package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import lombok.Builder;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;

@Builder
public class QuestionWordDto {
    
    public Integer index;
    
    public WordDto wordDto;

}
