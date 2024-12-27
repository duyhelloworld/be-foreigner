package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.AccountToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;

import java.util.Optional;

@Repository
public interface AccountTokenRepo extends JpaRepository<AccountToken, Integer> {
    
    boolean existsByTypeAndToken(TokenType type, String token);

    // @Query("""
    //         SELECT at FROM AccountToken at
    //         WHERE expiredAt > CURRENT_TIMESTAMP 
    //             AND type = :type 
    //             AND token = :token
    //         """)
    // Optional<AccountToken> findByTypeAndToken(@Param("type") TokenType type, @Param("token") String token);

    @Query("""
            SELECT at FROM AccountToken at
            WHERE type = :type 
                AND expiredAt > CURRENT_TIMESTAMP 
                AND owner = :owner
            """)
    Optional<AccountToken> findValidTokenByTypeAndOwner(@Param("type") TokenType type, @Param("owner") String owner);
}
