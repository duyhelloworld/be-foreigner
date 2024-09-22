package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateQuestionDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface IQuestionService {

    PagingResult<QuestionDto> getAll(PagingRequest pagingRequest);

    QuestionDto getById(Integer id);

    QuestionDto addNew(CreateQuestionDto createQuestionDto);

    void delete(Integer id);
}
