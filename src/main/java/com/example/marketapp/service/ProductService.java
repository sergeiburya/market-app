package com.example.marketapp.service;

import com.example.marketapp.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryIn(List<Long> categoriesId);
}
