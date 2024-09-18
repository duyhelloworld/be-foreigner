package vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;

public interface IUserQuestionBookmarkService {
    
    PagingResult<QuestionDto> getQuestionBookmarked(PagingRequest pagingRequest, Integer id);

    void bookmark(User user, Integer questionId);

    void remove(Integer bookmarkId);
    
}
