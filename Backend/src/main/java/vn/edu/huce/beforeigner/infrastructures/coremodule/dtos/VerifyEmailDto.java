package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.ValidCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Valid
public class VerifyEmailDto {

    @NotBlank(message = "CODE_MISSING")
    @Pattern(regexp = "\\d{6}", message = "CODE_INVALID")
    @Size(message = "CODE_INVALID")
    private String code;
}
