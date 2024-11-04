package vn.edu.huce.beforeigner.domains.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;

import java.util.List;


@Repository
public interface CloudFileRepository extends JpaRepository<CloudFile, Integer> {
    
    List<CloudFile> findByType(CloudFileType type);

}
