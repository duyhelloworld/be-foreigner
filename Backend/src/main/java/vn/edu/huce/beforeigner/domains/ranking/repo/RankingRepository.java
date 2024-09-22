package vn.edu.huce.beforeigner.domains.ranking.repo;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.huce.beforeigner.domains.ranking.Ranking;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

}
