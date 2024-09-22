package vn.edu.huce.beforeigner.infrastructures.filemodule.impls;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Api.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ImageType;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ResourceResponse;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.UploadResponse;
import vn.edu.huce.beforeigner.utils.CloudinaryUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final Cloudinary cloudinary;

    private final AppObjectMapper objectMapper;

    private final static String RESOURCE_TYPE = "image";

    @Override
    public String getImage(ImageType type, String publicId) {
        String folder = getFolderName(type);
        var options = CloudinaryUtils.getOptions();
        // https://cloudinary.com/documentation/admin_api#get_the_details_of_a_single_resource
        // Do mode hiện tại chỉ là fixed nên lấy trong folder bằng prefix
        options.put("prefix", folder);
        try {
            ApiResponse response = cloudinary.api().resource(publicId, options);
            ResourceResponse resource = objectMapper.convertValue(response, ResourceResponse.class);
            return resource.getUrl();
        } catch (Exception e) {
            log.error("Error when getImage with publicId={} : {}", publicId, e.getMessage());
            return "";
        }
    }

    @Override
    public UploadResponse save(MultipartFile file, ImageType type) {
        try {
            var options = CloudinaryUtils.getOptions();
            String folder = getFolderName(type);
            options.put("folder", folder);
            options.put("display_name", file.getOriginalFilename());
            options.put("asset_folder", folder);
            options.put("resource_type", RESOURCE_TYPE);
            ResourceResponse resource = objectMapper
                    .convertValue(cloudinary.uploader().upload(file.getInputStream(), options), ResourceResponse.class);
            return UploadResponse.builder()
                .fileUrl(resource.getUrl())
                .publicId(resource.getPublicId())
                .build();
        } catch (IOException e) {
            log.error("Error when read and write content of '{}' : ", file.getOriginalFilename(), e);
            return null;
        }
    }

    @Override
    public void delete(String publicId) {
        var options = CloudinaryUtils.getOptions();
        options.put("return_error", true);
        try {
            Map<?,?> response = cloudinary.uploader().destroy(publicId, options);
            if (response.containsKey("error")) {
                log.error("Error when delete resource {} : {}", publicId, response.get("error").toString());
            } else {
                log.info("Deleted '{}'!");
            }
        } catch (Exception e) {
            log.error("Error when delete {} : ", publicId, e.getMessage());
        }
    }

    private String getFolderName(ImageType type) {
        return type.name().replace("_", "").toLowerCase(Locale.US);
    }
}
