package vn.edu.huce.beforeigner.infrastructures.filemodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    
    private String fileUrl;

    private String publicId;
}
