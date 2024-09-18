package vn.edu.huce.beforeigner.domains.vocab.repo;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.base.AuditedRepository;
import vn.edu.huce.beforeigner.domains.vocab.Example;

@Repository
public interface ExampleRepository extends AuditedRepository<Example> {
    
}
