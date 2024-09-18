package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.detail.LessonDetailDto;

@Component
@AllArgsConstructor
public class LessonMapper {
    
    private QuestionMapper questionMapper;

    public LessonDetailDto toDetailDto(Lesson lesson, Set<UserDto> users) {
        return LessonDetailDto.builder()
            .coverImage(lesson.getCoverImage())
            .description(lesson.getDescription())
            .diffLevel(lesson.getDiffLevel())
            .name(lesson.getName())
            .totalScore(lesson.getTotalScore())
            .totalTime(lesson.getTotalTime())
            .questions(lesson.getQuestions().stream()
                .map(q -> questionMapper.toDto(q))
                .collect(Collectors.toSet()))
            .learners(users)
            .learntCount(users.size())
            .build();
    }

    public LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
            .coverImage(lesson.getCoverImage())
            .diffLevel(lesson.getDiffLevel())
            .name(lesson.getName())
            .id(lesson.getId())
            .learntCount(lesson.getUserLessonStatistics().size())
            .build();
    }
}
