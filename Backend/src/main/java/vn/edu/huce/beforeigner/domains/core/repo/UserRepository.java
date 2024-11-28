package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.edu.huce.beforeigner.domains.core.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);


    @Query("SELECT u FROM User u WHERE u.role = 'USER' AND u.isAllowMail = true OR u.isAllowNotification = true")
    List<User> findUsersWantBeNotify();

    Optional<User> findByIdOrUsername(Integer id, String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isFirstTry = false")
    int resetFirstTry();
}
