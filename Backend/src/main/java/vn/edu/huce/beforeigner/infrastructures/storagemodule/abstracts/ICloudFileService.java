package vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts;

import org.springframework.web.multipart.MultipartFile;

import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;

public interface ICloudFileService {
    
    CloudFile save(MultipartFile file, CloudFileType type);

    void delete(CloudFile cloudFile);

    // Dùng trong OnStartup
    CloudFile saveAndGet(CloudFileType type, String word);

    String getUrl(CloudFileType type, String word);
    
}
