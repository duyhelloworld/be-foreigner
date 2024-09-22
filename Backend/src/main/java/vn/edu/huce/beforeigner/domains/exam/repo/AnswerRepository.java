package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
    List<Answer> findByQuestionId(Integer id);
}
