package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.exam.QuestionLevel;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers.AnswerOptionDto;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {
    
    public QuestionType type;

    public QuestionLevel level;

    public String correctOptionMean;

    public String correctOptionAudio;

    public List<AnswerOptionDto> answerOptions;

    public String sentenseAudio;

    public String sentenseMeaning;

    public String[] sentenseWords;

    public String[] unrelatedWords;
}
