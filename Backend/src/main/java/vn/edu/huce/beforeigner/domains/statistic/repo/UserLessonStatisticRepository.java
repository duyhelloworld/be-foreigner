package vn.edu.huce.beforeigner.domains.statistic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.statistic.UserLessonStatistic;

@Repository
public interface UserLessonStatisticRepository extends JpaRepository<UserLessonStatistic, Integer> {
    
}
