package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.constants.LessonConstants;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.domains.roadmap.RoadmapLesson;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Bài học
 */
public class Lesson extends FullAuditedEntity {

    /**
     * Mã bài học
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Mẫu ngữ pháp. Lưu JSON cố định
     */
    private String grammarNote;

     /**
     * Tên bài
     */
    @Column(nullable = false, length = 200)
    private String name;
    
    /**
     * Màu nền. Đặt theo rgb : #111000
     */
    @Column(nullable = false, length = 7)
    private String color = LessonConstants.DEFAULT_COLOR_CODE;

    /**
     * Lọai bài học
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonType type = LessonConstants.DEFAULT_LESSON_TYPE;

    /**
     * Độ khó
     * {@link UserLevel}
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel = LessonConstants.DEFAULT_USER_LEVEL;

    /**
     * Mức người dùng cần thiết để truy cập bài học
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonAccessLevel accessLevel = LessonConstants.DEFAULT_LESSON_ACCESS_LEVEL;

    /**
     * Url Ảnh bìa
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String coverImageUrl;

    /**
     * Mã file ảnh bìa
     */
    @Column(length = 20)
    private String coverImagePublicId;

    /**
     * Điểm + được
     */
    @Column(nullable = false)
    private Integer elo;

    /**
     * Các lịch sử học bài này
     */
    @OneToMany(mappedBy = "lesson")
    private Set<LessonHistory> lessonHistories = new HashSet<>();

    /**
     * Các câu hỏi trong bài
     */
    @OneToMany(mappedBy = "lesson")
    private Set<Question> questions = new HashSet<>();

    /**
     * Các lộ trình mà bài học thuộc về
     */
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<RoadmapLesson> roadmapLessons;
}