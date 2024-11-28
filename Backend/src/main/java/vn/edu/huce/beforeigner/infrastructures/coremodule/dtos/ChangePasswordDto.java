package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Data;

@Data
public class ChangePasswordDto {
    
    private String newPassword;

    private String oldPassword;

}
