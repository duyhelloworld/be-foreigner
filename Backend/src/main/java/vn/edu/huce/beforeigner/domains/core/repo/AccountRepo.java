package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameOrEmail(String username, String email);

    Optional<Account> findByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
        SELECT u FROM Account u
        JOIN AccountSetting as ON as.owner = u.username
        WHERE u.role = 'USER' AND as.isEnabled AND as.settingType IN ('LEARN_REMIND', 'WORD_LEARNING')
        """)
    List<Account> findUsersWantBeNotify();

    Optional<Account> findByIdOrUsername(Integer id, String username);

    Page<Account> findByRole(Role role, PageRequest pageRequest);
}
