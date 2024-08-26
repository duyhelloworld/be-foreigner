package vn.edu.huce.beforeigner.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Card;

@Repository
public interface CardRepo extends AuditedRepo<Card> {
    
    Set<Card> findByIdIn(List<Integer> ids);
}
