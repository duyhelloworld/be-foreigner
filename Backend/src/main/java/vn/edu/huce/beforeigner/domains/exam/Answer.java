package vn.edu.huce.beforeigner.domains.exam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Đáp án của câu hỏi
 */
public class Answer extends FullAuditedEntity {
    
    /**
     * Mã câu trả lời
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Có phải đáp án đúng không
     */
    @JoinColumn(nullable = false)
    private boolean isTrue;
    
    /**
     * Mã từ vựng của đáp án
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Word word;

    /**
     * Câu hỏi mà đáp án này thuộc về
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question question;
}
