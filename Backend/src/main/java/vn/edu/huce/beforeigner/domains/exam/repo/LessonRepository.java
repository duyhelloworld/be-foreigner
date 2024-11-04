package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.exam.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    
    @Query("SELECT l FROM Lesson l JOIN l.lessonHistories lh")
    // AND lh.status <> LessonStatus.LOCKED
    // AND l.level = :user.level
    // WHERE lh.createdBy = :user.id
    Page<Lesson> findByUser(Pageable pageable, @Param("user") User user);
}
