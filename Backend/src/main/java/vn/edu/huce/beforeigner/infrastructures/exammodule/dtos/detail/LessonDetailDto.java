package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.detail;

import java.time.Duration;
import java.util.Set;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.exam.DifficultyLevel;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;

@Builder
public class LessonDetailDto {

    public String name;

    public String coverImage;

    public Integer learntCount;
    
    public Duration totalTime;
    
    public String description;
    
    public Float totalScore;
    
    public DifficultyLevel diffLevel;
    
    public Set<UserDto> learners;

    public Set<QuestionDto> questions;
}
