package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UpdateProfileDto {
    
    private String fullname;

    private MultipartFile avatar;
    
}
