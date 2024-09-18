package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.search;

import lombok.Data;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;

@Data
public class SearchResultDto {
    
    private PagingResult<WordDto> words;

    private PagingResult<TopicDto> topics;
    
}
