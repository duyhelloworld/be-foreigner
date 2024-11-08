package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class CreateExampleDto {
    
    @NotBlank(message = "")
    private String sentense;

    private String mean;
}
