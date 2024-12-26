package vn.edu.huce.beforeigner.domains.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Setter;
import vn.edu.huce.beforeigner.constants.EloConstants;
import vn.edu.huce.beforeigner.domains.base.NoDeleteAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.Getter;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
/**
 * Lịch sử bài học
 */
public class LessonHistory extends NoDeleteAuditedEntity {

    /**
     * Khóa chính
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Trạng thái bài học
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonHistoryStatus status;

    /**
     * Điểm xếp hạng
     * @see EloConstants
     */
    private Integer elo;

    /**
     * Tổng thời gian làm
     */
    private Long totalTime;

    /**
     * Độ chính xác
     */
    private Integer accuracy;

    /**
     * Bài học
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Lesson lesson;
}
