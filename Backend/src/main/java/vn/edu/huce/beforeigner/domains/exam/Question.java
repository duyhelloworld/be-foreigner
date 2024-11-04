package vn.edu.huce.beforeigner.domains.exam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Question extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    /**
     * Dùng trong câu rearrange by audio
     * @see QuestionType#GIVE_AUDIO_REARRANGE_WORDS
     */
    private String sentenseAudio;

    @ManyToMany(mappedBy = "questions")
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();
}
