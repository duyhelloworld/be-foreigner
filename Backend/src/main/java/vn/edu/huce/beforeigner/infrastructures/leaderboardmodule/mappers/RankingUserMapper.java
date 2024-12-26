package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.leaderboard.RankingUser;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos.RankingUserDto;

@Component
public class RankingUserMapper {

    public RankingUserDto toDto(RankingUser leaderboardUser, Account user) {
        return RankingUserDto.builder()
                .userRank(leaderboardUser.getUserRank())
                .userId(user.getId())
                .elo(leaderboardUser.getElo())
                .avatar(user.getAvatarUrl())
                .username(user.getUsername())
                .build();
    }
}
