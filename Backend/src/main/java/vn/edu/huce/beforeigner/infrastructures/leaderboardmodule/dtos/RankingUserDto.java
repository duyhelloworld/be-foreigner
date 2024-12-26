package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos;

import lombok.Builder;

@Builder
public class RankingUserDto {

    public Integer userRank;

    public Integer elo;

    public String username;

    public Integer userId;
    
    public String avatar;
}
