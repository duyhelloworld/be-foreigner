package vn.edu.huce.beforeigner.domains.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuditedRepository<T extends Audited> extends JpaRepository<T, Integer> {
    
}
