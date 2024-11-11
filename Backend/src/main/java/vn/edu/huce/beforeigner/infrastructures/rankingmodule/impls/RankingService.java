package vn.edu.huce.beforeigner.infrastructures.rankingmodule.impls;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.ranking.repo.RankedUserRepository;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.abstracts.IRankingService;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos.RankingDto;

@Service
@RequiredArgsConstructor
public class RankingService implements IRankingService {

    private final RankedUserRepository rankedUserRepo;

    @Override
    public RankingDto fetch(User user) {
        var rankedusers = rankedUserRepo.getRankedUserDtos(user.getLevel());
        return RankingDto.builder()
                .users(rankedusers)
                .fetchedOn(LocalDateTime.now())
                .build();
    }

}
