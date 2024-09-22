package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.exam.DifficultyLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    
    Optional<Lesson> findFirstByDiffLevel(DifficultyLevel diffLevel);

}
