package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.LearnProcess;

@Repository
public interface LearnProcessRepo extends AuditedRepo<LearnProcess> {
    
}
