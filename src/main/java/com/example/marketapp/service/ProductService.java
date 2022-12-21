package com.example.marketapp.service;

import com.example.marketapp.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to,
                                        Pageable pageRequest);

    List<Product> getAllByCategoryIdIn(List<Long> categoriesId);
}
