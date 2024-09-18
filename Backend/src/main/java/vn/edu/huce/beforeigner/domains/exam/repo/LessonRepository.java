package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.base.AuditedRepository;
import vn.edu.huce.beforeigner.domains.exam.DifficultyLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import java.util.Optional;

@Repository
public interface LessonRepository extends AuditedRepository<Lesson> {
    
    Optional<Lesson> findFirstByDiffLevel(DifficultyLevel diffLevel);

}
