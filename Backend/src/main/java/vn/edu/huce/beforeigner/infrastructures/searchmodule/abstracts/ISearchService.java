package vn.edu.huce.beforeigner.infrastructures.searchmodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.search.SearchResultDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;

public interface ISearchService {
    
    SearchResultDto search(PagingRequest pagingRequest, String keyword);
    
}
