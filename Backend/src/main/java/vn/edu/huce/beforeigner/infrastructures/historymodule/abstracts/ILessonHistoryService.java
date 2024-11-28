package vn.edu.huce.beforeigner.infrastructures.historymodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.historymodule.dtos.LessonHistoryDto;

public interface ILessonHistoryService {
    
    List<LessonHistoryDto> getMyHistory(User user);
    
}
