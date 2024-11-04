package vn.edu.huce.beforeigner.domains.history;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.Getter;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LessonHistory extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Lesson lesson;

    /**
     * Số lần được làm lại
     */
    // private Integer retryCount;

    /**
     * Chỉ số câu hỏi làm lần cuối
     */
    // private Integer lastQuestionIndex;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;
}
