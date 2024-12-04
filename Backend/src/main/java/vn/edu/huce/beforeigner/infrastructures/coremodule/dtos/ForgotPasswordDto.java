package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.ValidPass;
import vn.edu.huce.beforeigner.annotations.ValidUsername;

@Data
public class ForgotPasswordDto {
    
    @NotBlank(message = "CODE_MISSING")
    private String code;

    @ValidUsername
    private String username;

    @ValidPass
    private String newPassword;
    
}
