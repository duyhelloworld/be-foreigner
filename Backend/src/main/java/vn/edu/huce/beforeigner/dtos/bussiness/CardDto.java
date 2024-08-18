package vn.edu.huce.beforeigner.dtos.bussiness;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {

    private Integer id;

    private String imageUrl;

    private WordDto word;
}
