package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Data;

@Data
public class ForgotPasswordDto {
    
    private String username;

    private String code;

    private String newPassword;
    
}
