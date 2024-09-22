package vn.edu.huce.beforeigner.domains.history.repo;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;

@Repository
public interface LessonHistoryRepository extends JpaRepository<LessonHistory, Integer> {
    
}
