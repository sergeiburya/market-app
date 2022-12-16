package com.example.marketapp.controller;

import com.example.marketapp.dto.ProductRequestDto;
import com.example.marketapp.dto.ProductResponseDto;
import com.example.marketapp.dto.mapper.ProductMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping("/add")
    @ApiOperation(value = "save the new Product to DB ")
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.modelToDto(productService
                .save(productMapper.dtoToModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find Product by Id to DB")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.modelToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Product by ID to DB")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update Product by ID to DB")
    public ProductResponseDto update(@PathVariable Long id,
                                    @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.dtoToModel(productRequestDto);
        product.setId(id);
        return productMapper.modelToDto(productService.save(product));
    }

    @GetMapping("/findAllByPrice")
    @ApiOperation(value = "find all Products by price between two values, "
            + "page to page, sorted by title")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to,
             @RequestParam (defaultValue = "5")
             @ApiParam(defaultValue = "value to default is 5") Integer count,
             @RequestParam (defaultValue = "0")
             @ApiParam (defaultValue = "value to default is 0") Integer page,
             @RequestParam (defaultValue = "title")
             @ApiParam (defaultValue = "value to default is title") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return productService.findAllByPriceBetween(from, to, pageRequest,sortBy)
                .stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategoryId(@RequestParam List<Long> categoriesId) {
        return productService.getAllByCategoryIdIn(categoriesId)
                .stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
