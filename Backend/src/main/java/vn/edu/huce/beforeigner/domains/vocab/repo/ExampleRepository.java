package vn.edu.huce.beforeigner.domains.vocab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.vocab.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Integer> {
    
}
