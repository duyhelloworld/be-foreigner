package vn.edu.huce.beforeigner.utils.paging;

import java.util.Collection;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PagingResult<T> {

    private Integer totalPage;
    
    private Collection<T> items;
    
    public static <S, T> PagingResult<T> of(Page<S> items, Function<S, T> function) {
        PagingResult<T> result = new PagingResult<>();
        result.items = items.stream().map(s -> function.apply(s)).toList();
        result.totalPage = items.getTotalPages();
        return result;
    }
}