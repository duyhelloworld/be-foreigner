package vn.edu.huce.beforeigner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.core.InvalidToken;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Integer> {
    
    boolean existsByToken(String token);

}
