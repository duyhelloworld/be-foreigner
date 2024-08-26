package vn.edu.huce.beforeigner.entities.learn;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.entities.base.Audited;
import lombok.Getter;

@Getter
@Setter
@Entity
public class MeanExample extends Audited {

    private String mean;

    private String example;

    @ManyToOne
    private Word word;
}
