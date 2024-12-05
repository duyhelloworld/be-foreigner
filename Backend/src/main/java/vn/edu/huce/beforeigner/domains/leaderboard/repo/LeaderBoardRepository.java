package vn.edu.huce.beforeigner.domains.leaderboard.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoard;
import java.util.Optional;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardType;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Integer> {
    
    Optional<LeaderBoard> findByType(LeaderBoardType type);

}
