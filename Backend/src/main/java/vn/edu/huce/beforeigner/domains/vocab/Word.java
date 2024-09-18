package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.Audited;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Question;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Từ vựng
 */
public class Word extends Audited {

    /**
     * Giá trị từ vựng
     */
    @Column(nullable = false)
    private String value;

    /**
     * Ý nghĩa
     */
    @Column(nullable = false)
    private String mean;

    /**
     * Phiên âm
     */
    @Column(nullable = false)
    private String phonetic;

    /**
     * File âm thanh
     */
    @Column(nullable = false)
    private String audio;

    /**
     * Link ảnh thẻ
     */
    @Column(nullable = false)
    private String image;

    /**
     * Loại từ 
     */
    @Enumerated(EnumType.STRING)
    private WordType wordType;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Example> examples = new HashSet<>();

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "words", fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany(mappedBy = "words", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();
    
    public Word(String value, String mean, String phonetic, String audio, String image, WordType wordType) {
        this.value = value;
        this.image = image;
        this.mean = mean;
        this.phonetic = phonetic;
        this.audio = audio;
        this.wordType = wordType;
    }

    public void setTopicList(Topic... topics) {
        this.topics = Set.of(topics);
    }

    public void setExampleList(Example... examples) {
        this.examples = Set.of(examples);
    }
}
