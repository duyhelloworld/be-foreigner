package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import java.util.Set;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;

@Builder
public class QuestionDto {

    public QuestionType type;

    public Set<QuestionWordDto> words;

    public Set<AnswerDto> answers;
}