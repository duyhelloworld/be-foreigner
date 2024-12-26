package vn.edu.huce.beforeigner.domains.leaderboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.leaderboard.RankingUser;
import java.util.Optional;


@Repository
public interface RankingUserRepository extends JpaRepository<RankingUser, Integer> {
    
    Optional<RankingUser> findByOwner(String owner);

}
