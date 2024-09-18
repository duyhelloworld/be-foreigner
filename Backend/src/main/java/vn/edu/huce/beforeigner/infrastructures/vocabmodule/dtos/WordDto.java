package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class WordDto {

    public Integer id;

    public String value;

    public String mean;

    public String phonetic;

    public String audio;

    public String image;
}
