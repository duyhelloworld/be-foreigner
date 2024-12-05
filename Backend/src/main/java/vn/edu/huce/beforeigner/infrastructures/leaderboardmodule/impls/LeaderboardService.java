package vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.impls;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoard;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardType;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardUser;
import vn.edu.huce.beforeigner.domains.leaderboard.repo.LeaderBoardRepository;
import vn.edu.huce.beforeigner.domains.leaderboard.repo.LeaderBoardUserRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.abstracts.ILeaderboardService;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.dtos.LeaderBoardDto;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.mappers.LeaderBoardUserMapper;

@Service
@RequiredArgsConstructor
public class LeaderboardService implements ILeaderboardService {

    private final LeaderBoardRepository leaderBoardRepo;

    private final LeaderBoardUserRepository leaderBoardUserRepo;

    private final LeaderBoardUserMapper leaderBoardUserMapper;

    @Override
    @Transactional
    public LeaderBoardDto fetch(LeaderBoardType type) {
        LeaderBoard leaderBoard = leaderBoardRepo.findByType(type)
                .orElseThrow(() -> new AppException(ResponseCode.LEADER_BOARD_TYPE_UNDEFINED));
        return LeaderBoardDto.builder()
                .type(type)
                .users(leaderBoardUserMapper.toDtos(leaderBoard.getLeaderBoardUsers()))
                .build();
    }

    @Override
    public void updateUserRanks() {
        AtomicInteger rank = new AtomicInteger(1);
        var users = leaderBoardUserRepo.findAll();
        users.stream()
                .sorted(Comparator.comparingInt(LeaderBoardUser::getElo).reversed())
                .forEach(user -> user.setUserRank(rank.getAndIncrement()));
        leaderBoardUserRepo.saveAll(users);
    }

}
