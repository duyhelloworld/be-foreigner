package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.CorrectEmail;
import vn.edu.huce.beforeigner.annotations.CorrectUsername;

@Data
@Valid
public class RequestForgotPasswordDto {

    @CorrectUsername
    private String username;

    @CorrectEmail
    private String email;
}
