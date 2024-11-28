package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
    
    Optional<UserToken> findByTypeAndTokenAndIsDisabledFalse(TokenType type, String token);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE UserToken ut SET ut.isDisabled = true WHERE ut.lastModifiedBy = ?1 AND ut.type = ?2 AND ut.token = ?3")
    void disable(String lastModifiedBy, TokenType type, String token);

    Optional<UserToken> findByLastModifiedByAndType(String lastModifiedBy, TokenType type);

    boolean existsByLastModifiedByAndType(String lastModifiedBy, TokenType type);
}
