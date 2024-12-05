package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.ToString;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Data
@Valid
public class SignUpDto {

    private String fullname;

    @NotBlank(message = "CODE_MISSING")
    @Pattern(regexp = "\\d{6}", message = "CODE_INVALID")
    @Size(message = "CODE_INVALID")
    private String code;

    @NotBlank(message = "USERNAME_MISSING")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "EMAIL_MISSING")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PASSWORD_MISSING")
    @Min(value = 8, message = "PASSWORD_LENGTH_NOT_ENOUGH")
    @Pattern(regexp = "^[^\s]{8,}$'", message = "PASSWORD_INVALID")
    @ToString.Exclude
    private String password;

    private UserLevel level = UserLevel.BEGINNER;

    private MultipartFile avatar;
}
