package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.huce.beforeigner.domains.exam.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
