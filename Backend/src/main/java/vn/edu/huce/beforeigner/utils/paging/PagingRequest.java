package vn.edu.huce.beforeigner.utils.paging;

import org.springframework.data.domain.PageRequest;

import lombok.Builder;
import lombok.Setter;
import vn.edu.huce.beforeigner.constants.PagingConstants;

@Setter
@Builder
public class PagingRequest {
    
    @Builder.Default
    private Integer pageSize = PagingConstants.DEFAULT_PAGE_SIZE;
    
    @Builder.Default
    private Integer pageNumber = PagingConstants.DEFAULT_PAGE_NUMBER;

    public Integer getPageSize() {
        if (pageSize > PagingConstants.MAX_PAGE_SIZE) {
            pageSize = PagingConstants.MAX_PAGE_SIZE;
        } else if (pageSize < PagingConstants.DEFAULT_PAGE_SIZE) {
            pageSize = PagingConstants.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber < PagingConstants.DEFAULT_PAGE_NUMBER
            ? PagingConstants.DEFAULT_PAGE_NUMBER 
            : pageNumber;
    }

    public PageRequest pageable() {
        return PageRequest.ofSize(getPageSize())
            .withPage(getPageNumber());
    }
}
