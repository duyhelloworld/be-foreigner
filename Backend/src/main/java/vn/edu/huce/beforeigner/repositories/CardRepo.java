package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Card;

@Repository
public interface CardRepo extends AuditedRepo<Card> {
    
}
