package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Câu trả lời của câu hỏi
 */ 
public class Answer extends FullAudited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Dành cho câu hỏi chọn đáp án : true khi đúng, false khi sai, null khi không phải loại câu hỏi này
     * {@link QuestionType#CHOOSE_CORRECT_WORD}
     */
    private Boolean isTrue;

    /**
     * Dành cho câu hỏi sắp xếp : chỉ số của từ trong câu 
     * {@link QuestionType#REARRANGE_WORDS}
     * {@link QuestionType#REARRANGE_MEANS}
     */
    private Integer indexOfWord;

    /**
     * Từ trong đáp án
     * {@link QuestionType#REARRANGE_WORDS}
     * {@link QuestionType#REARRANGE_MEANS}
     * {@link QuestionType#CHOOSE_CORRECT_MEAN}
     * {@link QuestionType#CHOOSE_CORRECT_WORD}
     */
    @ManyToOne
    private Word word;
    
    /**
     * Dành cho câu hỏi nối : đáp án khác -> this
     * {@link QuestionType#MATCHING}
     */
    @OneToMany(mappedBy = "matchAnswer", cascade = CascadeType.PERSIST)
    private Set<Answer> matchedAnswers = new HashSet<>();

    /**
     * Dành cho câu hỏi nối: this -> đáp án    
     * {@link QuestionType#MATCHING}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer matchAnswer;

    /**
     * Câu hỏi
     */
    @ManyToOne
    private Question question;
}
