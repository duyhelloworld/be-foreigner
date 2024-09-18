package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import java.util.Set;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;

@Builder
public class QuestionDto {

    public String questionString;

    public QuestionType type;

    public String hint;

    public Integer score;

    public WordDto word;

    public Set<AnswerDto> answers;
}