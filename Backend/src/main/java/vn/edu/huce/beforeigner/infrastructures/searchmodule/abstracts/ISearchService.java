package vn.edu.huce.beforeigner.infrastructures.searchmodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.search.SearchResultDto;

public interface ISearchService {
    
    SearchResultDto search(PagingRequest pagingRequest, String keyword);
    
}
