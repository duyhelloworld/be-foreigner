package vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts;

import org.springframework.web.multipart.MultipartFile;

import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ImageType;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.UploadResponse;

public interface IImageService {
    
    String getImage(ImageType type, String publicId);
    
    UploadResponse save(MultipartFile file, ImageType type);

    void delete(String publicId);
    
}
