package com.example.marketapp.controller;

import com.example.marketapp.dto.CategoryRequestDto;
import com.example.marketapp.dto.CategoryResponseDto;
import com.example.marketapp.dto.mapper.CategoryMapper;
import com.example.marketapp.model.Category;
import com.example.marketapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapper.modelToDto(categoryService
                .save(categoryMapper.dtoToModel(categoryRequestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.modelToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.dtoToModel(categoryRequestDto);
        category.setId(id);
        return categoryMapper.modelToDto(category);
    }
}
