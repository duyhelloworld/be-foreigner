package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
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
public class Question extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    /**
     * URL file audio của câu hỏi
     * @see QuestionType#GIVE_AUDIO_REARRANGE_WORDS
     */
    private String sentenseAudio;

    /**
     * Các từ tạo nên câu hỏi , liên kết bởi " "
     * @see QuestionType#GIVE_SENTENSE_REARRANGE_WORDS
     */
    private String sentenseWords;

    /**
     * Nghĩa câu hỏi 
     * @see QuestionType#GIVE_SENTENSE_REARRANGE_WORDS
     */
    private String sentenseMeaning;

    /**
     * Các từ không liên quan
     * @see QuestionType#GIVE_SENTENSE_REARRANGE_WORDS
     */
    private String unrelatedWords;

    @Enumerated(EnumType.STRING)
    private QuestionLevel level;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lesson lesson;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();
}
