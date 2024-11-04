package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface ILessonService {
    
    /**
     * Lấy các lesson gợi ý cho user
     */
    PagingResult<LessonDto> getSuggestedLessons(PagingRequest pagingRequest, User user);

    /**
     * Học 1 bài học theo chỉ định
     * @param lessonId Học bài học chỉ định
     * @param user người học
     * @return
     */
    LessonDetailDto examine(Integer lessonId, User user);

    /**
     * Thử làm lại câu hỏi sai
     * @param questionId
     * @param user
     */
    void retry(Integer questionId, User user);

    // PagingResult<LessonDto> getAll(PagingRequest pagingRequest);

    // void addNew(CreateLessonDto createLessonDto);

    // LessonDto update(Integer id, UpdateLessonDto updateLessonDto);

    // void delete(Integer id);
    
}
