package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;

@Builder
public class LessonDto {

    public Integer id;

    public String name;

    public String cover;

    public Integer lastLearnIndex;
    
    public Integer totalQuestion;

    public LessonStatus status;
}
