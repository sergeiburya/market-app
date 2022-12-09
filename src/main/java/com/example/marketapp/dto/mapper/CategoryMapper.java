package com.example.marketapp.dto.mapper;

import com.example.marketapp.dto.CategoryRequestDto;
import com.example.marketapp.dto.CategoryResponseDto;
import com.example.marketapp.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CategoryMapper {
    public CategoryResponseDto modelToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(categoryResponseDto.getName());
        return categoryResponseDto;
    }

    public Category dtoToModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
