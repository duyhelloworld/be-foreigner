package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers.AnswerOptionDto;

@Component
public class AnswerMapper {
    
    public AnswerOptionDto toOptionDto(Answer answer) {
        return AnswerOptionDto.builder()
            .audio(answer.getAudio())
            .image(answer.getImage())
            .text(answer.getTxt())
            .build();
    }
}
