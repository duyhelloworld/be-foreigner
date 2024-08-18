package vn.edu.huce.beforeigner.entities.learn;

import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LearnProcess extends Audited {

    @ManyToOne
    private Deck deck;

    private Integer correctCount;

    private Integer incorrectCount;

    private LocalDateTime lastReviewedAt;
}
