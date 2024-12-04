package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.ToString;
import vn.edu.huce.beforeigner.annotations.ValidPass;
import vn.edu.huce.beforeigner.annotations.ValidUsername;

@Data
@Valid
public class SignInDto {
    
    @ValidUsername
    private String username;

    @ToString.Exclude
    @ValidPass
    private String password;
}