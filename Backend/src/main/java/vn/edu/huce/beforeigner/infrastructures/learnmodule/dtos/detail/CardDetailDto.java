package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;

@Data
@Builder
public class CardDetailDto {
    
    private Integer id;

    private String imageUrl;

    private WordDto word;

    private DeckDto deck;
}
