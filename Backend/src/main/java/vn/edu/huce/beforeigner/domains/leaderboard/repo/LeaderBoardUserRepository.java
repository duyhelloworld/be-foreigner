package vn.edu.huce.beforeigner.domains.leaderboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardUser;

@Repository
public interface LeaderBoardUserRepository extends JpaRepository<LeaderBoardUser, Integer> {
    
}
