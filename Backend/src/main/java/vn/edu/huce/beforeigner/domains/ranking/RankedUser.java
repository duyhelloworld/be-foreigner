package vn.edu.huce.beforeigner.domains.ranking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAuditedEntity;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.utils.RankUtils;
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
     * Hạng của user
     */
    private Integer userRank;

    /**
     * Điểm để xếp hạng
     * @see RankUtils#getElo(User)
     */
    private Integer elo;

    @ManyToOne
    private Ranking ranking;
}
