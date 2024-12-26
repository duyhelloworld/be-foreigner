package vn.edu.huce.beforeigner.domains.vocab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.vocab.Sentense;
import java.util.List;
import java.util.Set;


@Repository
public interface SentenseRepository extends JpaRepository<Sentense, Integer> {
    
    Set<Sentense> findByIdIn(List<Integer> ids);
}
