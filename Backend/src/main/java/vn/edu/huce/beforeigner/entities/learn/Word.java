package vn.edu.huce.beforeigner.entities.learn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;

@Getter
@Setter
@Entity
public class Word extends Audited {

    private String value;

    private String pronunciation;

    private String audioFile;

    @Enumerated(EnumType.STRING)
    private WordType wordType;

    @OneToOne(mappedBy = "word")
    private Card card;

    @OneToMany(mappedBy = "word")
    private List<WordExample> wordExamples = new ArrayList<>();

    @ManyToMany(mappedBy = "words")
    private Set<Category> categories = new HashSet<>();
}
