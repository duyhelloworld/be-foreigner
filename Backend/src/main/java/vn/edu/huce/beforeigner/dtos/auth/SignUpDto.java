package vn.edu.huce.beforeigner.dtos.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class SignUpDto {

    private String fullname;

    @NotBlank(message = "USERNAME_MISSING")
    private String username;

    @NotBlank(message = "EMAIL_MISSING")
    private String email;

    @NotBlank(message = "PASSWORD_MISSING")
    private String password;
}
