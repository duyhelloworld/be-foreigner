package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Bài học
 */
public class Lesson extends FullAuditedEntity {

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
     * Số kim cương khi hoàn thành
     */
    private Integer diamonds;

    /**
     * Điểm kinh nghiệm
     */
    private Integer experiences;

    /**
     * Độ khó
     * {@link UserLevel}
     */
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    /**
     * Loại bài học
     */
    private LessonType type;

    /**
     * Ảnh bìa
     */
    @ManyToOne
    private CloudFile coverImage;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.EAGER)
    private Set<LessonHistory> lessonHistories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    public Lesson(String name, Integer diamonds, Integer experiences, UserLevel level) {
        this.name = name;
        this.diamonds = diamonds;
        this.experiences = experiences;
        this.level = level;
    }
}
