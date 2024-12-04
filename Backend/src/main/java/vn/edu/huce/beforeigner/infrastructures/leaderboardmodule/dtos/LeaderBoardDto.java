package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos;

import java.util.List;

import lombok.Builder;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardType;

@Builder
public class LeaderBoardDto {
    
    public List<LeaderBoardUserDto> users;
    
    public LeaderBoardType type;
}
