package vn.edu.huce.beforeigner.domains.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;

import java.util.List;
import java.util.Optional;


@Repository
public interface CloudFileRepository extends JpaRepository<CloudFile, Integer> {
    
    List<CloudFile> findByType(CloudFileType type);

    Optional<CloudFile> findByFilename(String filename);

}
