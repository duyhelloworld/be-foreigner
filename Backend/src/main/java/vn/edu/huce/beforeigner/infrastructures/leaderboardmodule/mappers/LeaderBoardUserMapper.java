package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.mappers;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardUser;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos.LeaderBoardUserDto;

@Component
public class LeaderBoardUserMapper {

    public LeaderBoardUserDto toDto(LeaderBoardUser leaderboardUser) {
        return LeaderBoardUserDto.builder()
                .userRank(leaderboardUser.getUserRank())
                .userId(leaderboardUser.getUser().getId())
                .elo(leaderboardUser.getElo())
                .avatar(leaderboardUser.getUser().getAvatarUrl())
                .username(leaderboardUser.getUser().getUsername())
                .build();
    }

    public List<LeaderBoardUserDto> toDtos(Set<LeaderBoardUser> leaderBoardUsers) {
        return leaderBoardUsers.stream().map(lu -> toDto(lu)).toList();
    }
}
