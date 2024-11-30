package vn.edu.huce.beforeigner.domains.history.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface LessonHistoryRepository extends JpaRepository<LessonHistory, Integer> {
    
    List<LessonHistory> findByLessonAndOwner(Lesson lesson, String owner);

    List<LessonHistory> findByOwner(String owner);

}
