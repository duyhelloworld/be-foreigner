package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.exam.LessonType;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;

@Getter
@Setter
@Builder
public class LessonDto {

    private Integer id;

    private String name;

    private String cover;

    private LessonStatus status;

    private LessonType type;
}
