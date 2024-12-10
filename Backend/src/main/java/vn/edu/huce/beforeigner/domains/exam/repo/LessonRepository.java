package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    
    Page<Lesson> findByLevel(PageRequest pageRequest, UserLevel level);

}