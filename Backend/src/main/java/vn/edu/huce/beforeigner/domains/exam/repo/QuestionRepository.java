package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.base.AuditedRepository;
import vn.edu.huce.beforeigner.domains.exam.Question;

@Repository
public interface QuestionRepository extends AuditedRepository<Question> {
    
}
