package vn.edu.huce.beforeigner.entities.learn;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;

@Getter
@Setter
@Entity
public class WordExample extends Audited {

    private String meaning;

    private String sentence;

    @ManyToOne
    private Word word;
}
