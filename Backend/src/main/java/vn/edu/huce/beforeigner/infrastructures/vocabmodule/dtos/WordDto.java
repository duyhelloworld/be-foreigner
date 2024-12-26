package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WordDto {

    private Integer id;

    private String value;

    private String mean;

    private String phonetic;

    private String audio;

    private String image;
}
