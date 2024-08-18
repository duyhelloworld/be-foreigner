package vn.edu.huce.beforeigner.entities.learn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Card extends Audited {

    private String imageUrl;

    @OneToOne
    private Word word;

    @ManyToOne
    private Deck deck;
}
