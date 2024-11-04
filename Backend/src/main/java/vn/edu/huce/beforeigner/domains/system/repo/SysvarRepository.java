package vn.edu.huce.beforeigner.domains.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.system.Sysvar;
import vn.edu.huce.beforeigner.domains.system.SysvarKey;

import java.util.Optional;

@Repository
public interface SysvarRepository extends JpaRepository<Sysvar, Integer> {

    Optional<Sysvar> findByKey(SysvarKey key);
}
