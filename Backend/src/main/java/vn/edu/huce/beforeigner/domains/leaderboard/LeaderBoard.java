package vn.edu.huce.beforeigner.domains.leaderboard;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Các bảng xếp hạng
 */
public class LeaderBoard extends FullAuditedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LeaderBoardType type;

    @OneToMany(mappedBy = "leaderBoard", fetch = FetchType.EAGER)
    private Set<LeaderBoardUser> leaderBoardUsers = new HashSet<>();
}
