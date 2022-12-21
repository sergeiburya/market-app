package com.example.marketapp.service.impl;

import com.example.marketapp.model.Product;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
                                               Pageable pageRequest) {
        return productRepository.findAllByPriceBetween(from, to, pageRequest);
    }

    @Override
    public List<Product> getAllByCategoryIdIn(List<Long> categoriesId) {
        return productRepository.findAllByCategoryIdIn(categoriesId);
    }
}
