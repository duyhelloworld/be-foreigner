package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import java.util.Set;

import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.AnswerDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.updatation.UpdateAnswerDto;

public interface IAnswerService {
    
    Set<AnswerDto> getAllByQuestion(Integer questionId);

    AnswerDto update(UpdateAnswerDto updateAnswerDto);

    void delete(Set<Integer> ids);
}
