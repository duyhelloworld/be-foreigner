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
                .level(question.getLevel())
                .index(question.getIndexInLesson())
                .sentenseMeaning(Optional.ofNullable(question.getSentense())
                        .map(s -> s.getMean())
                        .orElse(null)) // đề
                .sentenseAudio(Optional.ofNullable(question.getSentense())
                        .map(s -> s.getAudioUrl())
                        .orElse(null)) // đề
                .sentenseWords(Optional.ofNullable(question.getSentense())
                        .map(s -> s.getValue().split(" "))
                        .orElse(null)) // các từ của đáp án
                .unrelatedWords(Optional.ofNullable(question.getUnrelatedWords())
                        .map(uw -> uw.split(" "))
                        .orElse(null)); // từ ko liên quan của đáp án
    }
}
