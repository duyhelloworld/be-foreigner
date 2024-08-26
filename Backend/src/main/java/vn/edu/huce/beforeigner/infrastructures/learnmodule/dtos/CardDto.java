package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {

    private Integer id;

    private String imageUrl;

    private WordDto word;
}
