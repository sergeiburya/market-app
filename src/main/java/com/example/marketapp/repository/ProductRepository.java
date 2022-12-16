package com.example.marketapp.repository;

import com.example.marketapp.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to, Pageable pageable);

    List<Product> findAllByCategoryIdIn(List<Long> categoriesId);
}
