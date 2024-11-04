package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.answers.AnswerOption;

@Component
public class AnswerMapper {
    
    public AnswerOption toOptionDto(Answer answer) {
        return AnswerOption.builder()
            .audio(answer.getAudio())
            .image(answer.getImage())
            .text(answer.getTxt())
            .build();
    }
}
