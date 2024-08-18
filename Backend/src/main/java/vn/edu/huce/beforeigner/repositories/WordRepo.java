package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Word;

@Repository
public interface WordRepo extends AuditedRepo<Word> {
    
}
