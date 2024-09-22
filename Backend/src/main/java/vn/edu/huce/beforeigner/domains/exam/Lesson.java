package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.base.CloudinaryImage;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Bài học
 */
public class Lesson extends FullAudited implements CloudinaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
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

    private String publicId;

    /**
     * Màu ảnh nền (code)
     */
    private String background;

    /**
     * Tổng điểm
     */
    private Integer diamonds;

    /**
     * Điểm kinh nghiệm
     */
    private Integer experience;

    /**
     * Độ khó
     * {@link DifficultyLevel}
     */
    @Enumerated(EnumType.STRING)
    private DifficultyLevel diffLevel;

    @ManyToMany
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<LessonHistory> lessonHistories = new HashSet<>();
}
