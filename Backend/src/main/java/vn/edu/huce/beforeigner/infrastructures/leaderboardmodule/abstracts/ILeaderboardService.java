package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.abstracts;

import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardType;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos.LeaderBoardDto;

public interface ILeaderboardService {
    
    LeaderBoardDto fetch(LeaderBoardType type);

    void updateUserRanks();

}
