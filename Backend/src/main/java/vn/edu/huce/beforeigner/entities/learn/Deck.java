package vn.edu.huce.beforeigner.entities.learn;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;

@Getter
@Setter
@Entity
public class Deck extends Audited {
    
    private String name;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;

    public Deck() {
    }

    public Deck(String name, String description, DifficultyLevel difficultyLevel) {
        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
    }

    @OneToMany
    @JoinColumn(name = "deck_id")
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "deck")
    private Set<LearnProcess> learnProcess = new HashSet<>();
}
