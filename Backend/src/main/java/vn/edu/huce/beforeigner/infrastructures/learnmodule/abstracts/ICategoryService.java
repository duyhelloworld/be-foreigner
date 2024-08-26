package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateCategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.CategoryDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.updatation.UpdateCategoryDto;

public interface ICategoryService {
    List<CategoryDto> getAll(String keyword);

    CategoryDetailDto getById(Integer id);

    void addNew(CreateCategoryDto createCategoryDto);

    CategoryDetailDto update(Integer categoryId, UpdateCategoryDto updateCategoryDto);

    void delete(Integer id);
}
