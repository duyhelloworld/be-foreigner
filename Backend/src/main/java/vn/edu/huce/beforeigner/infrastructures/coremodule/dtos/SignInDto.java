package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;

@Data
@Valid
public class SignInDto {
    
    @NotBlank(message = "USERNAME_MISSING")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "PASSWORD_MISSING")
    @Min(value = 8, message = "PASSWORD_LENGTH_NOT_ENOUGH")
    @Pattern(regexp = "^(?!.*\\s)[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{8,}$", message = "PASSWORD_INVALID")
    private String password;
}