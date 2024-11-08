package vn.edu.huce.beforeigner.domains.ranking;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAuditedEntity;
import lombok.Getter;

@Getter
@Setter
@Entity
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
    private Integer rank;

    /**
     * Điểm để xếp hạng
     */
    private Long elo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ranking ranking;

    public RankedUser(Integer rank, Long elo, String owner) {
        this.rank = rank;
        this.elo = elo;
        this.setOwner(owner);
    }
}
