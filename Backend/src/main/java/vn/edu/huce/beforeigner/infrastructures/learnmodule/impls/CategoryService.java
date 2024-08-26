package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Category;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.ICategoryService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateCategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.CategoryDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.updatation.UpdateCategoryDto;
import vn.edu.huce.beforeigner.mappers.business.CategoryMapper;
import vn.edu.huce.beforeigner.repositories.CategoryRepo;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private CategoryRepo categoryRepo;

    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll(String keyword) {
        return categoryRepo.findAll()
            .stream()
            .filter(c -> StringUtils.hasText(keyword) && c.getName().contains(keyword))
            .map(c -> categoryMapper.toDto(c))
            .toList();
    }

    @Override
    public CategoryDetailDto getById(Integer id) {
        return categoryMapper.toDetailDto(categoryRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.CATEGORY_NOT_FOUND)));
    }

    @Override
    public void addNew(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
        categoryRepo.save(category);
    }

    @Override
    public CategoryDetailDto update(Integer categoryId, UpdateCategoryDto updateCategoryDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.deleteById(id);
    }
    
}
