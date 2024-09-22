package vn.edu.huce.beforeigner.infrastructures.filemodule.dtos;

import lombok.Data;

@Data
public class ResourceResponse {
    // Product mode
    private String assetId;
    private String assetFolder;
    // Product mode

    private String publicId;

    private String format;

    private String resourceType;

    private Long version;

    private String createdAt;

    private Long width;

    private Long height;

    private String displayName;

    private String url;

    private String secureUrl;
}
