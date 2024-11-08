package vn.edu.huce.beforeigner.utils.paging;

import java.util.Collection;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingResult<T> {

    private Integer totalPage;

    private Collection<T> items;

    public static <S, T> PagingResult<T> of(Page<S> page, Function<S, T> mapFunction) {
        return new PagingResult<>(page.getTotalPages(),
                page.stream().map(s -> mapFunction.apply(s)).toList());
    }

    public static <T> PagingResult<T> of(Page<T> page) {
        return new PagingResult<>(page.getTotalPages(),
                page.getContent());
    }
}