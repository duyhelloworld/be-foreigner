package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    
    @Query("SELECT l FROM Lesson l JOIN l.lessonHistories lh WHERE l.level = :level OR lh.owner = :username")
    Page<Lesson> getRecentLessonsAndLessonsWithSameLevel(Pageable pageable,
    @Param("username") String username, @Param("level") UserLevel level);
    
}
