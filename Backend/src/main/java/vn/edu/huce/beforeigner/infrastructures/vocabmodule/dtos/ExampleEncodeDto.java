package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExampleEncodeDto {

    private String text;

    private String[] bolds;
}
