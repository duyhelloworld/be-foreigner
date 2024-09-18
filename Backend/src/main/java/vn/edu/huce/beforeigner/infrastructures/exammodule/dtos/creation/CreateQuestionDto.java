package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation;

import java.util.Set;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;

@Data
@Valid
public class CreateQuestionDto {
    
    private Integer wordId;

    private String questionString;

    private QuestionType type;

    private String hint;

    private Integer score;

    private Set<CreateAnswerDto> answers;
}
