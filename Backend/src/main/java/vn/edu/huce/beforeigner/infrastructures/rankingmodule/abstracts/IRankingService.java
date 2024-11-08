package vn.edu.huce.beforeigner.infrastructures.rankingmodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos.RankingDto;

public interface IRankingService {
    
    RankingDto fetch(User user);

}
