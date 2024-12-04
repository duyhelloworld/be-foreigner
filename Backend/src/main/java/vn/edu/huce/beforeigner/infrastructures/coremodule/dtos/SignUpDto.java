package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.ToString;
import vn.edu.huce.beforeigner.annotations.ValidCode;
import vn.edu.huce.beforeigner.annotations.ValidEmail;
import vn.edu.huce.beforeigner.annotations.ValidPass;
import vn.edu.huce.beforeigner.annotations.ValidUsername;
import vn.edu.huce.beforeigner.domains.common.UserLevel;

@Data
@Valid
public class SignUpDto {

    private String fullname;

    @ValidCode
    private String code;

    @ValidUsername
    private String username;

    @ValidEmail
    private String email;

    @ValidPass
    @ToString.Exclude
    private String password;

    private UserLevel level;
    
    private MultipartFile avatar;
}
