package vn.edu.huce.beforeigner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import vn.edu.huce.beforeigner.entities.base.Audited;

@NoRepositoryBean
public interface AuditedRepo<T extends Audited> extends JpaRepository<T, Integer> {
    
}
