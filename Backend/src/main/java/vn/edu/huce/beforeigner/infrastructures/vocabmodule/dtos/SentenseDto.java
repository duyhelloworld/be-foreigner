package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SentenseDto {

    private Integer id;

    private String value;

    private String mean;
}
