package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Example;

@Repository
public interface ExampleRepository extends AuditedRepository<Example> {
    
}