package vn.edu.huce.beforeigner.dtos.bussiness.detail;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.dtos.bussiness.DeckDto;
import vn.edu.huce.beforeigner.dtos.bussiness.WordDto;

@Data
@Builder
public class CardDetailDto {
    
    private Integer id;

    private String imageUrl;

    private WordDto word;

    private DeckDto deck;
}
