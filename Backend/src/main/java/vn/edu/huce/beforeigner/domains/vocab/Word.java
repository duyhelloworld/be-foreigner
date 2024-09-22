package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.base.CloudinaryImage;
import vn.edu.huce.beforeigner.domains.exam.Answer;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Word extends FullAudited implements CloudinaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Giá trị từ vựng
     */
    @Column(nullable = false)
    private String value;

    /**
     * Nghĩa ở từ  
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
    @Column(nullable = false)
    private String audio;

    /**
     * Link ảnh thẻ
     */
    @Column(nullable = false)
    private String image;

    /**
     * Id ảnh
     */
    @Column(length = 50)
    private String publicId;

    /**
     * Loại từ 
     */
    @Enumerated(EnumType.STRING)
    private WordType wordType;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Example> examples = new HashSet<>();

    @ManyToMany(mappedBy = "words", fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();

    public Word(String value, String mean, String phonetic, String audio, String image, WordType wordType) {
        this.value = value;
        this.mean = mean;
        this.phonetic = phonetic;
        this.audio = audio;
        this.image = image;
        this.wordType = wordType;
    }
}
