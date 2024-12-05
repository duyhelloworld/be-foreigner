package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Data
@Valid
public class ChangePasswordDto {

    @NotBlank(message = "CODE_MISSING")
    @Pattern(regexp = "\\d{6}", message = "CODE_INVALID")
    @Size(message = "CODE_INVALID")
    private String code;

    @NotBlank(message = "PASSWORD_MISSING")
    @Min(value = 8, message = "PASSWORD_LENGTH_NOT_ENOUGH")
    @Pattern(regexp = "^[^\s]{8,}$'", message = "PASSWORD_INVALID")
    private String newPassword;

    @NotBlank(message = "PASSWORD_MISSING")
    @Min(value = 8, message = "PASSWORD_LENGTH_NOT_ENOUGH")
    @Pattern(regexp = "^[^\s]{8,}$'", message = "PASSWORD_INVALID")
    private String oldPassword;
}
