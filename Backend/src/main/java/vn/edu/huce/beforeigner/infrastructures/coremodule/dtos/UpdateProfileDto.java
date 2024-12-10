package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileDto {
    
    @Size(min = 1, max = 255, message = "FULLNAME_OUT_MAX_SIZE")
    private String fullname;

    private String avatar;
}
