package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class RenewTokenDto {

    @NotBlank(message = "REFRESH_TOKEN_MISSING")
    private String refreshToken;

}
