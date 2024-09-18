package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.WordMapper;

@Component
@AllArgsConstructor
public class QuestionMapper {
    
    private WordMapper wordMapper;

    private AnswerMapper answerMapper;

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
        .word(wordMapper.toDto(question.getWord()))
        .questionString(question.getQuestionString())
        .score(question.getScore())
        .answers(question.getAnswers().stream().map(a -> answerMapper.toDto(a)).collect(Collectors.toSet()))
        .type(question.getType())
        .hint(question.getHint())
        .build();
    }
}
