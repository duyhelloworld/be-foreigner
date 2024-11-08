package vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos.unflash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnflashImageResultDto {
    private String id;

    private String slug;
    
    private String createdAt;
    
    private String updatedAt;
    
    private String promotedAt;
    
    private int width;
    
    private int height;
    
    private String color;
    
    private String blurHash;
    
    private String altDescription;
    
    private String assetType;
    
    private UnflashImageResultUrlDto urls;
}
