package vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos.unflash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnflashImageResultUrlDto {

    private String raw;

    private String full;

    private String regular;

    private String small;

    private String thumb;
    
    private String smallS3;
}
