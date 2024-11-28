package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.Set;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions.QuestionDto;

@Component
@AllArgsConstructor
public class LessonMapper {

    public LessonDetailDto toDetailDto(Lesson lesson, Set<QuestionDto> questionDtos) {
        return LessonDetailDto.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .elo(lesson.getElo())
                .questions(questionDtos)
                .build();
    }

    public LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .cover(lesson.getCoverImageUrl())
                .type(lesson.getType())
                .status(lesson.getLessonHistories().stream()
                        .findFirst()
                        .map(history -> history.getStatus())
                        .orElse(LessonStatus.LOCKED))
                .build();
    }
}
