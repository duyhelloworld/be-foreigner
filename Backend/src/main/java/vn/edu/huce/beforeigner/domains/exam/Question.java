package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.vocab.Sentense;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Câu hỏi bài học
 */
public class Question extends FullAuditedEntity {

    /**
     * Mã câu hỏi
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Loại câu hỏi
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    /**
     * Cấp độ câu hỏi
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;

    /**
     * Mã bài học
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Lesson lesson;

    /**
     * Số thứ tự câu hỏi trong bài học
     */
    @Column(nullable = false)
    private Integer indexInLesson;

    /**
     * Dùng trong các câu chọn đáp án dựa trên 1 từ. Đây là đáp án đúng và nó duy nhất
     * @see QuestionType#GIVE_SENTENSE_REARRANGE_WORDS
     * @see QuestionType#GIVE_AUDIO_REARRANGE_WORDS
     * @see QuestionType#GIVE_SENTENSE_CHECK_RECORD
     * @see QuestionType#GIVE_AUDIO_ENTER_SENTENSE
     */
    @ManyToOne
    private Sentense sentense;

    /**
     * Các từ không liên quan, join bởi dấu cách " "
     * @see QuestionType#GIVE_SENTENSE_REARRANGE_WORDS
     * @see QuestionType#GIVE_AUDIO_REARRANGE_WORDS
     */
    @Lob
    private String unrelatedWords;

    /**
     * Dùng trong các câu chọn đáp án dựa trên 1 từ. Đây là đáp án đúng và nó duy nhất
     * @see QuestionType#LEARN_WORD
     * @see QuestionType#GIVE_WORD_CHECK_RECORD
     * @see QuestionType#GIVE_MEAN_ENTER_WORD
     */
    @ManyToOne
    private Word word;

    /**
     * Danh sách các câu trả lời
     * @see QuestionType#GIVE_MEAN_CHOOSE_WORD
     * @see QuestionType#GIVE_AUDIO_CHOOSE_WORD
     */
    @OneToMany(
        mappedBy = "question",
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();
}
