package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateLessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.detail.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.updatation.UpdateLessonDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface ILessonService {
    
    LessonDetailDto learn(Integer lessonId, User user);

    PagingResult<LessonDto> getAll(PagingRequest pagingRequest);

    void addNew(CreateLessonDto createLessonDto);

    LessonDto update(Integer id, UpdateLessonDto updateLessonDto);

    void delete(Integer id);
    
}
