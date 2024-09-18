package vn.edu.huce.beforeigner.domains.exam;

import vn.edu.huce.beforeigner.constants.GapConstants;
import vn.edu.huce.beforeigner.domains.base.Audited;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateAnswerDto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Câu hỏi
 */
public class Question extends Audited {
    
    /**
     * Từ cần ôn
     */
    @ManyToOne
    private Word word;

    /**
     * - Câu hỏi loại {@link QuestionType#FILL_IN_BLANK}
     * <pre>
     * Format : "They laugh {} me because I'm different. I {} at them because they're all the same"
     * Answer : "at, laugh"
     * </pre>
     * 
     * - Câu hỏi loại {@link QuestionType#CHOOSE_CORRECT_WORD}
     * !!! Just a string !!!
     * 
     * @see 
     * @see GapConstants#GAP_DETECTOR 
     * @see CreateAnswerDto
     */
    private String questionString;

    /**
     * Loại câu hỏi
     * {@link QuestionType}
     */
    private QuestionType type;

    /**
     * Gợi ý
     */
    private String hint;
    
    /**
     * Điểm của câu hỏi
     */
    private Integer score;

    @ManyToMany
    private Set<Lesson> lessons = new HashSet<>();
    
    @OneToMany(mappedBy = "question")
    private Set<Bookmark> bookmarks = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    public Question(Word word, QuestionType type, String hint, Integer score) {
        this.type = type;
        this.word = word;
        this.hint = hint;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Question {word=" + word + ", questionString=" + questionString + ", type=" + type + ", hint="
                + hint + ", score=" + score + "}";
    }

    public void setAnswers(Answer... answers) {
        this.answers = Set.of(answers);
    }
}
