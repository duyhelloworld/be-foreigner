package vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    private String publicId;

    private String filename;

    private String url;
}
