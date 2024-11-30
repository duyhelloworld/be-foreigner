package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers.AnswerOptionDto;

@Component
public class AnswerMapper {

    public AnswerOptionDto toOptionDto(Answer answer) {
        return AnswerOptionDto.builder()
                .audio(answer.getWord().getAudioUrl())
                .image(answer.getWord().getImageUrl())
                .value(answer.getWord().getValue())
                .isTrue(answer.isTrue())
                .build();
    }
}