package vn.edu.huce.beforeigner.domains.core.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.TokenType;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
    
    boolean existsByToken(String token);

    Optional<UserToken> findByToken(String token);

    @Query("select t from UserToken t where t.owner = :username and type = :type")
    Optional<UserToken> findByUsernameAndType(@Param("username") String username, @Param("type") TokenType type);
}
