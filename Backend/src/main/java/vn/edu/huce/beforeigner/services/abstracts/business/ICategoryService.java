package vn.edu.huce.beforeigner.services.abstracts.business;

import java.util.List;

import vn.edu.huce.beforeigner.dtos.bussiness.CategoryDto;
import vn.edu.huce.beforeigner.dtos.bussiness.creatation.CreateCategoryDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.CategoryDetailDto;
import vn.edu.huce.beforeigner.dtos.bussiness.updatation.UpdateCategoryDto;

public interface ICategoryService {
    List<CategoryDto> getAll(String keyword);

    CategoryDetailDto getById(Integer id);

    void addNew(CreateCategoryDto createCategoryDto);

    CategoryDetailDto update(Integer categoryId, UpdateCategoryDto updateCategoryDto);

    void delete(Integer id);
}
