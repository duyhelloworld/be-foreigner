package vn.edu.huce.beforeigner.domains.history.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.history.LessonHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface LessonHistoryRepository extends JpaRepository<LessonHistory, Integer> {
    
    @Query("SELECT lh FROM LessonHistory lh JOIN lh.lesson l WHERE l.id = :lessonId AND lh.owner = :userid")
    Optional<LessonHistory> findByLessonIdAndOwner(@Param("lessonId") Integer lessonId, @Param("userid") String userid);
}
