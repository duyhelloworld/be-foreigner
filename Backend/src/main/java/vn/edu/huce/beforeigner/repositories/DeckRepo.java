package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Deck;

@Repository
public interface DeckRepo extends AuditedRepo<Deck> {
    
}
