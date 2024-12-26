package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class UpdateSentenseDto {
    
    @NotBlank(message = "SENTENSE_MEAN_MISSING")
    private String mean;

    @NotBlank(message = "SENTENSE_VALUE_MISSING")
    private String value;

    private MultipartFile audio;
}
