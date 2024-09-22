package vn.edu.huce.beforeigner.domains.ranking.repo;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.huce.beforeigner.domains.ranking.RankedUser;

@Repository
public interface RankedUserRepository extends JpaRepository<RankedUser, Integer> {

}
