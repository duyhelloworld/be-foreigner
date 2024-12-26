package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SentenseDetailDto {
    
    private Integer id;

    private String value;

    private String mean;

    private String audio;

    private String image;
}
