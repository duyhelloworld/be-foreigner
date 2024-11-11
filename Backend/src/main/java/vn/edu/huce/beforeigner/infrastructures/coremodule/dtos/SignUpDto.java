package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "AVATAR_MISSING")
    private MultipartFile avatar;

    // @NotNull(message = "NOTIFICATION_TOKEN_MISSING")
    // private String notificationToken;
}
