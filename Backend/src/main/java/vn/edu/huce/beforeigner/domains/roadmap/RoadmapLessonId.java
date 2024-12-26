package vn.edu.huce.beforeigner.domains.roadmap;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

/**
 * Cặp khóa chính bảng roadmap_lesson
 */
@Embeddable
@EqualsAndHashCode
public class RoadmapLessonId {
    
    public Integer roadmapId;
    
    public Integer lessonId;
    
}
