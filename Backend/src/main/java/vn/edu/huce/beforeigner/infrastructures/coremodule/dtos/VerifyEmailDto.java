package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.ValidCode;

@Data
@Valid
public class VerifyEmailDto {
    
    @ValidCode
    private String code;
}
