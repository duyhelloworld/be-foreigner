package vn.edu.huce.beforeigner.infrastructures.storagemodule.impls;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.domains.storage.repo.CloudFileRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos.ResourceResponse;
import vn.edu.huce.beforeigner.utils.CloudinaryUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudFileService implements ICloudFileService {

    private final Cloudinary cloudinary;

    private final CloudFileRepository cloudFileRepo;

    private final AppObjectMapper objectMapper;

    @Override
    public CloudFile save(MultipartFile file, CloudFileType type) {
        try {
            var options = CloudinaryUtils.getOptions();
            var folderName = CloudFileType.getFolderName(type);
            options.put("folder", folderName);
            options.put("display_name", file.getOriginalFilename());
            options.put("asset_folder", folderName);
            options.put("resource_type", "auto");
            options.put("return_error", true);
            var uploadResponse = cloudinary.uploader().upload(file.getInputStream(), options);
            if (uploadResponse.containsKey("error")) {
                throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
            }

            ResourceResponse resource = objectMapper
                    .convertValue(uploadResponse, ResourceResponse.class);
            CloudFile cloudFile = new CloudFile();
            cloudFile.setPublicId(resource.getPublicId());
            cloudFile.setType(type);
            cloudFile.setFilename(file.getOriginalFilename());
            cloudFile.setUrl(resource.getUrl());
            return cloudFileRepo.save(cloudFile);
        } catch (IOException e) {
            log.error("Error when read and write content of '{}' : ", file.getOriginalFilename(), e);
            throw new AppException(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public void delete(CloudFile cloudFile) {
        var options = CloudinaryUtils.getOptions();
        options.put("return_error", true);
        try {
            Map<?,?> response = cloudinary.uploader().destroy(cloudFile.getPublicId(), options);
            if (response.containsKey("error")) {
                log.error("Error when delete resource {} : {}", cloudFile.getPublicId(), response.get("error").toString());
            } else {
                cloudFileRepo.delete(cloudFile);
                log.info("Deleted '{}'!");
            }
        } catch (IOException e) {
            log.error("Error when delete {} : ", cloudFile.getPublicId(), e.getMessage());
        }
    }
}
