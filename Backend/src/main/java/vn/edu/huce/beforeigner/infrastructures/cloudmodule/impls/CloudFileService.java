package vn.edu.huce.beforeigner.infrastructures.cloudmodule.impls;

import java.io.IOException;
import java.util.Map;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;

import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.ResourceResponse;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.UploadResponse;
import vn.edu.huce.beforeigner.utils.CloudinaryUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudFileService implements ICloudFileService {

    private final Cloudinary cloudinary;

    private final AppObjectMapper objectMapper;

    @Override
    public UploadResponse save(MultipartFile file, CloudFileType type) {
        try {
            if (file != null) {
                var options = CloudinaryUtils.getOptions();
                var folderName = CloudFileType.getFolderName(type);
                options.put("folder", folderName);
                options.put("display_name", file.getOriginalFilename());
                options.put("asset_folder", folderName);
                options.put("resource_type", "auto");
                options.put("return_error", true);
                var uploadResponse = cloudinary.uploader().upload(file.getBytes(), options);
                if (uploadResponse.containsKey("error")) {
                    throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
                }
                ResourceResponse resource = objectMapper
                        .convertValue(uploadResponse, ResourceResponse.class);
                return UploadResponse.builder().publicId(resource.getPublicId()).filename(file.getOriginalFilename())
                        .url(resource.getUrl()).build();
            } else {
                return UploadResponse.builder()
                .url(type.getDefaultUrl()).build();
            }
        } catch (IOException e) {
            log.error("Error when read and write content of '{}' : ", file.getOriginalFilename(), e.getMessage());
            throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public void delete(String publicId) {
        var options = CloudinaryUtils.getOptions();
        options.put("return_error", true);
        options.put("public_id", publicId);
        try {
            Map<?, ?> response = cloudinary.uploader().destroy(publicId, options);
            if (response.containsKey("error")) {
                log.error("Error when delete resource {} : {}", publicId, response.get("error").toString());
            } else {
                log.info("Deleted '{}'!");
            }
        } catch (IOException e) {
            log.error("Error when delete {} : ", publicId, e.getMessage());
        }
    }

    @Override
    public UploadResponse save(String base64, String filename, CloudFileType fileType) {
        try {
            if (base64 != null && !base64.isBlank()) {
                var file = Base64.getDecoder().decode(base64);
                var options = CloudinaryUtils.getOptions();
                var folderName = CloudFileType.getFolderName(fileType);
                options.put("folder", folderName);
                options.put("display_name", filename);
                options.put("asset_folder", folderName);
                options.put("resource_type", "auto");
                options.put("return_error", true);
                var uploadResponse = cloudinary.uploader().upload(file, options);
                if (uploadResponse.containsKey("error")) {
                    throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
                }
                ResourceResponse resource = objectMapper
                        .convertValue(uploadResponse, ResourceResponse.class);
                return UploadResponse.builder().publicId(resource.getPublicId()).filename(filename)
                        .url(resource.getUrl()).build();
            } else {
                return UploadResponse.builder()
                .url(fileType.getDefaultUrl()).build();
            }
        } catch (IOException e) {
            log.error("Error when read and write content of '{}' : ", filename, e.getMessage());
            throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }
}
