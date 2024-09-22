package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.detail.LessonDetailDto;

@Component
@AllArgsConstructor
public class LessonMapper {
    
    private QuestionMapper questionMapper;

    public LessonDetailDto toDetailDto(Lesson lesson) {
        return LessonDetailDto.builder()
            .coverImage(lesson.getCoverImage())
            .description(lesson.getDescription())
            .diffLevel(lesson.getDiffLevel())
            .name(lesson.getName())
            .questions(lesson.getQuestions().stream()
                .map(q -> questionMapper.toDto(q))
                .collect(Collectors.toSet()))
            .learntCount(lesson.getLessonHistories().size())
            .build();
    }

    public LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
            .coverImage(lesson.getCoverImage())
            .diffLevel(lesson.getDiffLevel())
            .name(lesson.getName())
            .id(lesson.getId())
            .build();
    }
}
