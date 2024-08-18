package vn.edu.huce.beforeigner.dtos.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@Valid
public class SignInDto {
    
    @NotBlank(message = "USERNAME_MISSING")
    private String username;

    @ToString.Exclude
    @NotBlank(message = "PASSWORD_MISSING")
    private String password;
}