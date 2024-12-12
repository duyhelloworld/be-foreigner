package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Lesson;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Từ vựng
 */
public class Word extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Từ vựng
     */
    @Column(nullable = false)
    private String value;

    /**
     * Nghĩa của từ  
     */
    private String mean;

    /**
     * Phiên âm
     */
    @Column(nullable = false)
    private String phonetic;

    /**
     * File âm thanh
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String audioUrl;

    private String audioPublicId;

    private String audioFilename;

    /**
     * File ảnh 
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    private String imagePublicId;

    private String imageFilename;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Example> examples = new HashSet<>();

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();

    // @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    // private Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "words", fetch = FetchType.LAZY)
    private Set<Lesson> lessons = new HashSet<>();
}
