package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.AnswerDto;

@Component
@AllArgsConstructor
public class AnswerMapper {

    public AnswerDto toDto(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .isTrue(answer.getIsTrue())
                .matchId(answer.getMatchAnswer().getId())
                .correctString(answer.getCorrectString())
                .build();
    }

}
