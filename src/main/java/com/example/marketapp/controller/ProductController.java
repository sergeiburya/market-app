package com.example.marketapp.controller;

import com.example.marketapp.dto.ProductRequestDto;
import com.example.marketapp.dto.ProductResponseDto;
import com.example.marketapp.dto.mapper.ProductMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.modelToDto(productService
                .save(productMapper.dtoToModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.modelToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                    @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.dtoToModel(productRequestDto);
        product.setId(id);
        return productMapper.modelToDto(productService.save(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategoryId(@RequestParam List<Long> categoriesId) {
        return productService.getAllByCategoryIn(categoriesId)
                .stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
