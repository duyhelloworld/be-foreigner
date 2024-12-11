package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import jakarta.validation.constraints.NotBlank;

@Data
@Valid
public class SetupDto {
    
    @NotBlank(message = "NOTIFICATION_TOKEN_MISSING")
    private String token;

    private UserLevel level;

}