package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
public class CreateExampleDto {
    private String sentense;

    private String mean;
}
