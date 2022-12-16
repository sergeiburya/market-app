package com.example.marketapp.service.impl;

import com.example.marketapp.model.Product;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.util.ProductSortService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductSortService productSortService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductSortService productSortService) {
        this.productRepository = productRepository;
        this.productSortService = productSortService;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to,
                                               PageRequest pageRequest, String sortBy) {
        return productRepository.findAllByPriceBetween(from, to,
                pageRequest.withSort(productSortService.getSort(sortBy)));
    }

    @Override
    public List<Product> getAllByCategoryIdIn(List<Long> categoriesId) {
        return productRepository.findAllByCategoryIdIn(categoriesId);
    }
}
