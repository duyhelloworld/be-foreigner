package vn.edu.huce.beforeigner.domains.ranking;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.core.UserLevel;
import lombok.Getter;

@Getter
@Setter
@Entity
/**
 * Các bảng xếp hạng
 */
public class Ranking extends FullAudited {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Cấp độ người dùng
     */
    private UserLevel level;

    @OneToMany(mappedBy = "ranking")
    private Set<RankedUser> rankedUsers = new HashSet<>();

}
