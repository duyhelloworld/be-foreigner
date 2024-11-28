package vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts;

import org.springframework.web.multipart.MultipartFile;

import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.UploadResponse;

public interface ICloudFileService {
    
    UploadResponse save(MultipartFile file, CloudFileType fileType);

    void delete(String publicId);
}
