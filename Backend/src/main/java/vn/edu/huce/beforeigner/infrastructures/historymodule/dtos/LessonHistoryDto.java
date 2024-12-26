package vn.edu.huce.beforeigner.infrastructures.historymodule.dtos;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.history.LessonHistoryStatus;

@Builder
public class LessonHistoryDto {
    
    public String lessonImage;

    public Integer historyId;

    public Integer lessonId;

    public String lessonName;

    public Integer elo;

    public String startedAt;

    public String completedAt;
    
    public String totalTime;

    public LessonHistoryStatus status;

    public Integer accuracy;

}
