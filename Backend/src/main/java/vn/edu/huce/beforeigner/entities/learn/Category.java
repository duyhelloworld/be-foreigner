package vn.edu.huce.beforeigner.entities.learn;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends Audited {
    
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "categories_words")
    private Set<Word> words = new HashSet<>();

    public Category(String name, String description) { 
        this.name = name;
        this.description = description;
    }
}
