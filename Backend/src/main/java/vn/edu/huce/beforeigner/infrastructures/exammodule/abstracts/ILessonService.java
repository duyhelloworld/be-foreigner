package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.CompletedLessonDto;
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
     * Đánh dấu hoàn thành bài học
     * @param lessonId
     */
    void completed(CompletedLessonDto completedLessonDto, User user);

    
}
