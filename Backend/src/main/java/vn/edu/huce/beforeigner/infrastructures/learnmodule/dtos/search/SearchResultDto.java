package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.search;

import java.util.List;

import lombok.Data;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;

@Data
public class SearchResultDto {
    
    private List<WordDto> words;

    private List<DeckDto> decks;
}
