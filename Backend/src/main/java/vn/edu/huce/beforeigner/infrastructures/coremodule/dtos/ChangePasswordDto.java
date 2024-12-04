package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import vn.edu.huce.beforeigner.annotations.ValidCode;
import vn.edu.huce.beforeigner.annotations.ValidPass;

@Data
@Valid
public class ChangePasswordDto {

    @ValidCode
    private String code;

    @ValidPass
    private String newPassword;

    @ValidPass
    private String oldPassword;
}
