package vn.edu.huce.beforeigner.domains.exam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import lombok.Getter;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class QuestionWord extends FullAudited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Question question;

    @ManyToOne
    private Word word;

    /**
     * Chỉ số của từ trong câu hỏi sắp xếp
     * {@link QuestionType#REARRANGE_WORDS}
     * {@link QuestionType#REARRANGE_MEANS}
     */
    private Integer indexOfWord;
}
