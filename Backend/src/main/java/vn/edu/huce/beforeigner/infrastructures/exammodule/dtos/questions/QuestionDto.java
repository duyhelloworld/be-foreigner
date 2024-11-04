package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers.AnswerOption;

@Getter
@Setter
@JsonInclude(content = Include.NON_NULL)
public class QuestionDto {
    
    private QuestionType type;

    private AnswerOption correctOption;

    private Set<AnswerOption> incorrectOptions;

    private Set<String> correctSentenseWords;

    private String correctSentenseAudio;

    private Set<String> unrelatedWords;

    private Map<String, String> matchingAnswers;
}
