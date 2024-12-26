package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos;

import java.util.List;

import lombok.Builder;

@Builder
public class RankingDto {
    
    public List<RankingUserDto> users;

}
