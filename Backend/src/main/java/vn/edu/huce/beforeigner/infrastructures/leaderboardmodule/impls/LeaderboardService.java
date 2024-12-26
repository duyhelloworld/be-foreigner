package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.impls;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.leaderboard.RankingUser;
import vn.edu.huce.beforeigner.domains.leaderboard.repo.RankingUserRepository;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.abstracts.ILeaderboardService;

@Service
@RequiredArgsConstructor
public class LeaderboardService implements ILeaderboardService {

    // private final RankingRepository rankingRepo;

    private final RankingUserRepository rankingUserRepo;

    // private final RankingUserMapper rankingUserMapper;

    // @Override
    // @Transactional
    // public RankingDto fetch(RankingType type) {
    //     Ranking ranking = rankingRepo.findByType(type)
    //             .orElseThrow(() -> new AppException(ResponseCode.LEADER_BOARD_TYPE_UNDEFINED));
    //     return RankingDto.builder()
    //             .type(type)
    //             .users(rankingUserMapper.toDtos(ranking.getRankingUsers()))
    //             .build();
    // }

    @Override
    public void updateUserRanks() {
        AtomicInteger rank = new AtomicInteger(1);
        var users = rankingUserRepo.findAll();
        users.stream()
                .sorted(Comparator.comparingInt(RankingUser::getElo).reversed())
                .forEach(user -> user.setUserRank(rank.getAndIncrement()));
        rankingUserRepo.saveAll(users);
    }

}
