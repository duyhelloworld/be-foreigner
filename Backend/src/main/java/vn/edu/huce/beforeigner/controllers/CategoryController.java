package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.ICategoryService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateCategoryDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.CategoryDetailDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryController {

    private ICategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDto> getAll(@RequestParam(required = false) String keyword) {
        return categoryService.getAll(keyword);
    }
    
    @GetMapping("{id}")
    public CategoryDetailDto getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping("add")
    public void addNew(@RequestBody CreateCategoryDto createCategoryDto) {
        categoryService.addNew(createCategoryDto);
    }
}
