package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Example extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Câu ví dụ 
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sentense;

    /**
     * Ý nghĩa câu
     */
    @Column(nullable = false)
    private String mean;

    @ManyToOne
    private Word word;

    public Example(String sentense, String mean) {
        this.sentense = sentense;
        this.mean = mean;
    }
}
