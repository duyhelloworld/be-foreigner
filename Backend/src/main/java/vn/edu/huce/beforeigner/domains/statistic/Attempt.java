package vn.edu.huce.beforeigner.domains.statistic;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Lần làm bài của người dùng
 */
public class Attempt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Điểm của người dùng
     */
    private Float score;

    /**
     * Số câu đúng
     */
    private Integer correctCount;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    private UserLessonStatistic userLessonStatistic;
}
