package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos;

import lombok.Builder;

@Builder
public class LeaderBoardUserDto {

    public Integer userRank;

    public Integer elo;

    public String username;

    public Integer userId;
    
    public String avatar;
}
