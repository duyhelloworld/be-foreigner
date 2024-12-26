package vn.edu.huce.beforeigner.domains.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.core.AccountSetting;
import java.util.List;

@Repository
public interface AccountSettingRepo extends JpaRepository<AccountSetting, Integer> {
    
    @Query("""
        SELECT as FROM AccountSetting as
        JOIN Account a
        WHERE isEnabled = TRUE And a.id = :accountId
        """)
    List<AccountSetting> findEnabledSetting(@Param("accountId") Integer accountId);
}
