package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'USER' AND u.isAllowMail OR u.isAllowNotification OR u.isAllowWordNotification")
    List<User> findUsersWantBeNotify();

    Optional<User> findByIdOrUsername(Integer id, String username);

    Page<User> findByRole(Role role, PageRequest pageRequest);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isShowedStreak = false AND u.quota = ?1")
    int resetShowedStreak(Integer quota);
}
