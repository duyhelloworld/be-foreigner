package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
    @ManyToOne
    @JoinColumn(nullable = false)
    private CloudFile audio;

    /**
     * File ảnh 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private CloudFile image;

    /**
     * Loại từ 
     */
    @Enumerated(EnumType.STRING)
    private WordType wordType;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Example> examples = new HashSet<>();

    public Word(String value, String mean, String phonetic, WordType wordType) {
        this.value = value;
        this.mean = mean;
        this.phonetic = phonetic;
        this.wordType = wordType;
    }
}
