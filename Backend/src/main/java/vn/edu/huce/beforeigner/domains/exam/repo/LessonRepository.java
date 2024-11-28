package vn.edu.huce.beforeigner.domains.exam.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    
    Page<Lesson> findByLevel(PageRequest pageRequest, UserLevel level);

    @Query("SELECT l FROM Lesson l WHERE l.level = :userLevel ORDER BY FUNCTION('RAND') LIMIT 1")
    Optional<Lesson> findRandomLessonByUserLevel(@Param("userLevel") UserLevel userLevel);
}
