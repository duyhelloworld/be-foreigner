package vn.edu.huce.beforeigner.entities.learn;

import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LearnProcess extends Audited {
    
    private Integer correctCount;
    
    private Integer incorrectCount;
    
    @ManyToOne
    private Deck deck;
}
