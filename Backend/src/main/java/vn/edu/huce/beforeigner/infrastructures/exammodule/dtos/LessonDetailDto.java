package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions.QuestionDto;

@Builder
public class LessonDetailDto {
    
    public Integer id;
    
    public String name;

    public Integer diamonds;

    public Integer experiences;

    @Builder.Default
    public Set<QuestionDto> questions = new HashSet<>();
}
