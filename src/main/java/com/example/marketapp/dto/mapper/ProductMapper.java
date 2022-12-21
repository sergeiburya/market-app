package com.example.marketapp.dto.mapper;

import com.example.marketapp.dto.ProductRequestDto;
import com.example.marketapp.dto.ProductResponseDto;
import com.example.marketapp.model.Product;
import com.example.marketapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDto modelToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoriesId(product.getCategory().getId());
        return productResponseDto;
    }

    public Product dtoToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryRepository.getById(productRequestDto.getCategoriesId()));
        return product;
    }
}
