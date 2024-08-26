package vn.edu.huce.beforeigner.entities.learn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Word extends Audited {

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String phonetic;

    @Column(nullable = false)
    private String audioFile;

    @Enumerated(EnumType.STRING)
    private WordType wordType;

    @OneToOne(mappedBy = "word")
    private Card card;

    @OneToMany(mappedBy = "word")
    private List<MeanExample> wordExamples = new ArrayList<>();

    @ManyToMany(mappedBy = "words")
    private Set<Category> categories = new HashSet<>();

    public Word(String value, String phonetic, String audioFile, WordType wordType) {
        this.value = value;
        this.phonetic = phonetic;
        this.audioFile = audioFile;
        this.wordType = wordType;
    }
}
