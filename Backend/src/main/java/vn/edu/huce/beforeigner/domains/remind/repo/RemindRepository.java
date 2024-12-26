package vn.edu.huce.beforeigner.domains.remind.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.remind.Remind;
import vn.edu.huce.beforeigner.domains.remind.NotificationMethod;
import java.util.List;
import vn.edu.huce.beforeigner.domains.core.Account;


@Repository
public interface RemindRepository extends JpaRepository<Remind, String> {
    
    List<Remind> findByAccountAndMethod(Account account, NotificationMethod method);

    List<Remind> findByIdIn(List<Integer> ids);
}
