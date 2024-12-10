package vn.edu.huce.beforeigner.infrastructures.historymodule.dtos;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;

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

    public LessonStatus status;

    public Float accuracy;

}
