package vn.edu.huce.beforeigner.entities.learn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;

@Getter
@Setter
@Entity
public class Example extends Audited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sentense;

    @ManyToOne
    private Word word;

    public Example(String sentense) {
        this.sentense = sentense;
    }
}
