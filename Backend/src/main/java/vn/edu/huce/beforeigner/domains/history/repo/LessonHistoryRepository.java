package vn.edu.huce.beforeigner.domains.history.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


@Repository
public interface LessonHistoryRepository extends JpaRepository<LessonHistory, Integer> {
    
    List<LessonHistory> findByLessonAndOwner(Lesson lesson, String owner);

    List<LessonHistory> findByOwner(String owner);

    @Query("SELECT l FROM LessonHistory l WHERE l.owner = ?1 AND l.status = 'COMPLETED' ORDER BY COALESCE(l.updatedAt, l.createdAt) LIMIT 1")
    Optional<LessonHistory> findRemindLesson(String owner);

}
