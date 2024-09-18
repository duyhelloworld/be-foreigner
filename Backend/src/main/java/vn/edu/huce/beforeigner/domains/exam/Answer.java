package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.Audited;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Câu trả lời của câu hỏi
 */ 
public class Answer extends Audited {
    
    /**
     * Dành cho câu hỏi nối/chọn đáp án: giá trị đáp án
     * {@link QuestionType#MATCHING}
     * {@link QuestionType#CHOOSE_CORRECT_WORD}
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Word> words = new HashSet<>();
    
    /**
     * Dành cho câu hỏi nối: khóa ngoại this -> đáp án     
     * {@link QuestionType#MATCHING}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer matchAnswer;

    /**
     * Dành cho câu hỏi nối: các đáp án nối -> this
     * {@link QuestionType#MATCHING}
     */
    @OneToMany(mappedBy = "matchAnswer", cascade = CascadeType.PERSIST)
    private Set<Answer> matchedAnswers = new HashSet<>();

    /**
     * Dành cho câu hỏi chọn đáp án
     * {@link QuestionType#CHOOSE_CORRECT_WORD}
     */
    private Boolean isTrue;

    /**
     * Dành cho câu hỏi điền chỗ trống
     * Format : [""]
     * {@link QuestionType#FILL_IN_BLANK}
     */
    private String correctString;

    @ManyToOne
    private Question question;

    public Answer(Question question) {
        this.question = question;
    }

    public void setWords(Word... words) {
        this.words = Set.of(words);
    }
}
