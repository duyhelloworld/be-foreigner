package vn.edu.huce.beforeigner.domains.vocab;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.Audited;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Topic extends Audited {
    
    private String name;

    private String description;

    private String coverImage;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Word> words = new HashSet<>();
    
    public Topic(String name, String description, String coverImage) {
        this.name = name;
        this.description = description;
        this.coverImage = coverImage;
    }
}
