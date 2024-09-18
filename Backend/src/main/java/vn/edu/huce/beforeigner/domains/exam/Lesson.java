package vn.edu.huce.beforeigner.domains.exam;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.Audited;
import vn.edu.huce.beforeigner.domains.statistic.UserLessonStatistic;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Bài học
 */
public class Lesson extends Audited {

    /**
     * Tên bài
     */
    private String name;

    /**
     * Mô tả
     */
    private String description;

    /**
     * Ảnh bìa
     */
    private String coverImage;

    /**
     * Tổng điểm
     */
    private Float totalScore;

    /**
     * Độ khó
     * {@link DifficultyLevel}
     */
    @Enumerated(EnumType.STRING)
    private DifficultyLevel diffLevel;

    /**
     * Tổng thời gian làm bài
     */
    private Duration totalTime;

    @ManyToMany(mappedBy = "lessons")
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<UserLessonStatistic> userLessonStatistics = new HashSet<>();

    public Lesson(String name, String description, String coverImage, DifficultyLevel diffLevel,
            Duration totalTime, Float totalScore) {
        this.name = name;
        this.description = description;
        this.coverImage = coverImage;
        this.diffLevel = diffLevel;
        this.totalTime = totalTime;
        this.totalScore = totalScore;
    }
}
