package vn.edu.huce.beforeigner.domains.ranking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Xếp hạng user, dùng audit  để định danh 
 */
public class RankedUser extends OwnerAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Chỉ số trên BXH của user
     */
    private Integer userRank;

    /**
     * Điểm để xếp hạng
     */
    private Long elo;

    @ManyToOne
    private Ranking ranking;

    public RankedUser(Integer userRank, Long elo) {
        this.userRank = userRank;
        this.elo = elo;
    }
}
