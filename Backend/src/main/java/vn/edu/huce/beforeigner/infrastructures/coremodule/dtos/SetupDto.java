package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import jakarta.validation.Valid;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Valid
public class SetupDto {
    
    @NotBlank
    private String notificationToken;

}