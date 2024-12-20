package vn.edu.huce.beforeigner.infrastructures.historymodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.infrastructures.historymodule.dtos.LessonHistoryDto;
import vn.edu.huce.beforeigner.utils.DatetimeUtils;

@Component
public class LessonHistoryMapper {
    
    public LessonHistoryDto toDto(LessonHistory lessonHistory) {
        return LessonHistoryDto.builder()
            .historyId(lessonHistory.getId())
            .accuracy(lessonHistory.getAccuracy())
            .lessonId(lessonHistory.getLesson().getId())
            .lessonName(lessonHistory.getLesson().getName())
            .elo(lessonHistory.getLesson().getTarget().getElo())
            .lessonImage(lessonHistory.getLesson().getCoverImageUrl())
            .startedAt(DatetimeUtils.dateToString(lessonHistory.getCreatedAt()))
            .completedAt(DatetimeUtils.dateToString(lessonHistory.getUpdatedAt()))
            .totalTime(DatetimeUtils.formatDuration(lessonHistory.getTotalTime()))
            .status(lessonHistory.getStatus())
            .build();
    }
}
