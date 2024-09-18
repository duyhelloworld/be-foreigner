package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.base.AuditedRepository;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import java.util.List;


@Repository
public interface AnswerRepository extends AuditedRepository<Answer> {
    
    List<Answer> findByQuestionId(Integer id);
}
