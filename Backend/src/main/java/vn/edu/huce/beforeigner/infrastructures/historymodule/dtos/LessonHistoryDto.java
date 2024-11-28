package vn.edu.huce.beforeigner.infrastructures.historymodule.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;

@Builder
public class LessonHistoryDto {
    
    public String lessonImage;

    public Integer lessonId;

    public String lessonName;

    public Integer elo;

    public LocalDateTime startAt;
    
    public String totalTime;

    public LessonStatus status;

    public Float accuracy;

}
