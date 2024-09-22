package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;

@Component
@AllArgsConstructor
public class QuestionMapper {

    private QuestionWordMapper questionWordMapper;

    private AnswerMapper answerMapper;

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .words(question.getQuestionWords().stream().map(qw -> questionWordMapper.toDto(qw))
                        .collect(Collectors.toSet()))
                .answers(question.getAnswers().stream().map(a -> answerMapper.toDto(a)).collect(Collectors.toSet()))
                .type(question.getType())
                .build();
    }
}
