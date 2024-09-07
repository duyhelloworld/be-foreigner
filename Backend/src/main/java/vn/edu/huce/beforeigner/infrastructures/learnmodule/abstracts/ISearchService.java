package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.search.SearchResultDto;

public interface ISearchService {
    
    SearchResultDto search(String keyword);
    
}
