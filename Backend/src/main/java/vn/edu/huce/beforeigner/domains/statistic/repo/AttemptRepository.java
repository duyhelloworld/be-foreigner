package vn.edu.huce.beforeigner.domains.statistic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.statistic.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
    
}
