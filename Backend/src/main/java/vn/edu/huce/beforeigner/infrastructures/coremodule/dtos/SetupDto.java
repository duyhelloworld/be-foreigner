package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
public class SetupDto {
    
    private String notificationToken;

}