package vn.edu.huce.beforeigner.domains.history;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.Getter;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Getter
@Setter
@Entity
public class LessonHistory extends OwnerAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    /**
     * Trạng thái bài học
     */
    @Enumerated(EnumType.STRING)
    private LessonStatus status = LessonStatus.ONGOING;

    /**
     * Tổng thời gian làm, null nếu chưa xong
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Duration totalTime;

    @Column(scale = 2)
    private Float accuracy;
}
