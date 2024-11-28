package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;

import vn.edu.huce.beforeigner.domains.vocab.Word;
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
    @Column(nullable = false)
    private String name;

    /**
     * Mô tả
     */
    private String description;

    /**
     * Điểm xếp hạng
     */
    @Column(nullable = false)
    private Integer elo;

    /**
     * Độ khó
     * {@link UserLevel}
     */
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    /**
     * Loại bài học
     */
    @Enumerated(EnumType.STRING)
    private LessonType type;

    /**
     * Ảnh bìa
     */
    @Column(nullable = false)
    private String coverImageUrl;

    private String publicId;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.EAGER)
    private Set<LessonHistory> lessonHistories = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lesson")
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Word> words = new HashSet<>();
}