package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@Valid
public class SignUpDto {

    private String fullname;

    @NotBlank(message = "USERNAME_MISSING")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "EMAIL_MISSING")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PASSWORD_MISSING")
    @Pattern(regexp = "^(?!.*\\s)[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{8,}$", message = "PASSWORD_INVALID")
    private String password;

    private UserLevel level;

    private String avatarFilename;

    private String avatar;
}
