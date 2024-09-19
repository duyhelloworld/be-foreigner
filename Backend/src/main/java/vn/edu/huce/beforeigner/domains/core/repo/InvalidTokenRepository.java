package vn.edu.huce.beforeigner.domains.core.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.InvalidToken;
import vn.edu.huce.beforeigner.domains.core.User;


@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Integer> {
    
    boolean existsByToken(String token);

    Optional<InvalidToken> findByUser(User user);

    Optional<InvalidToken> findByToken(String token);

}
