package vn.edu.huce.beforeigner.domains.leaderboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.NoDeleteAuditedEntity;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class RankingUser extends NoDeleteAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userRank;
    
    @Column(nullable = false)
    private Integer elo;

    public void plus(Integer elo) {
        this.elo += elo;
    }
}
