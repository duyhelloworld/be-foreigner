package vn.edu.huce.beforeigner.domains.roadmap;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Bảng n-n roadmap và lesson
 */
public class RoadmapLesson {

    /**
     * Cặp khóa chính
     */
    @EmbeddedId
    private RoadmapLessonId roadmapLessonId;

    /**
     * Chỉ số bài học trong roadmap
     */
    @Column(nullable = false)
    private Integer lessonIndex;

    /**
     * Lộ trình
     */
    @ManyToOne
    @MapsId("roadmapId")
    @JoinColumn(name = "roadmap_id", nullable = false)
    private Roadmap roadmap;

    /**
     * Bài học
     */
    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}