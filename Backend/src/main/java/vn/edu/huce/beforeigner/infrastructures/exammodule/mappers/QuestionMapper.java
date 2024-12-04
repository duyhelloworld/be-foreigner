package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions.QuestionDto;

@Component
public class QuestionMapper {

    public QuestionDto.QuestionDtoBuilder toDto(Question question) {
        return QuestionDto.builder()
                .type(question.getType())
                .index(question.getIndexInLesson())
                .sentenseMeaning(question.getSentenseMeaning()) // đề
                .sentenseAudio(question.getSentenseAudio()) // đề
                .sentenseWords(Optional.ofNullable(question.getSentenseWords())
                        .map(sw -> sw.split(" "))
                        .orElse(null)) // các từ của đáp án
                .unrelatedWords(Optional.ofNullable(question.getUnrelatedWords())
                        .map(uw -> uw.split(" "))
                        .orElse(null)); // từ ko liên quan của đáp án
    }
}
