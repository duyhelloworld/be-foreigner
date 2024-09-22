package vn.edu.huce.beforeigner.domains.exam;

import vn.edu.huce.beforeigner.domains.base.FullAudited;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Câu hỏi
 */
public class Question extends FullAudited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Loại câu hỏi
     * {@link QuestionType}
     */
    private QuestionType type;

    /**
     * Các từ trong câu hỏi
     */
    @OneToMany(mappedBy = "question")
    private Set<QuestionWord> questionWords = new HashSet<>();

    @ManyToMany(mappedBy = "questions")
    private Set<Lesson> lessons = new HashSet<>();
    
    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();
}
