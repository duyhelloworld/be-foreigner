package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.ValidEmail;

@Data
@Valid
public class RequestVerifyEmailDto {
    
    @ValidEmail
    private String email;
}
