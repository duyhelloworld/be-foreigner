package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos;


import lombok.Builder;
import vn.edu.huce.beforeigner.domains.exam.DifficultyLevel;

@Builder
public class LessonDto {

    public Integer id;
    
    public String name;

    public String coverImage;

    public DifficultyLevel diffLevel;
}
