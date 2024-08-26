package vn.edu.huce.beforeigner.mappers.business;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Category;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.CategoryDetailDto;

@Component
@AllArgsConstructor
public class CategoryMapper {
    
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }

    public CategoryDetailDto toDetailDto(Category category) {
        return CategoryDetailDto.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .build();
    }
}
