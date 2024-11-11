package vn.edu.huce.beforeigner.domains.ranking.repo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.ranking.RankedUser;
import vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos.RankedUserDto;

@Repository
public interface RankedUserRepository extends JpaRepository<RankedUser, Integer> {
    
    @Query("SELECT new vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos.RankedUserDto(ru.userRank, ru.elo, u.username, avatar.url)"
    + " FROM RankedUser ru"
    + " JOIN User u ON u.username = ru.owner"
    + " JOIN u.avatar avatar" 
    + " JOIN ru.ranking r WHERE r.level = :level")
    List<RankedUserDto> getRankedUserDtos(@Param("level") UserLevel level);
}
