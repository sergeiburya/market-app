package com.example.marketapp.service.impl;

import com.example.marketapp.model.Category;
import com.example.marketapp.model.Product;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.service.util.ProductSortService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductSortService productSortService;

//    @Test
//    void findAllByPriceGreater100AndLess200() {
//        Product drill = new Product();
//        drill.setId(1L);
//        drill.setTitle("Drill Bosh 14v");
//        drill.setPrice(BigDecimal.valueOf(147));
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("tools");
//        drill.setCategory(category);
//        List<Long> categoriesId = new ArrayList<>();
//        List<Product> products = new ArrayList<>();
//        products.add(drill);
//        categoriesId.add(1L);
//        PageRequest pageRequest = PageRequest.of(0, 1);
//        Mockito.when(productRepository.findAllByPriceBetween(BigDecimal.valueOf(100),
//                BigDecimal.valueOf(200), pageRequest)).thenReturn(List.of(drill));
//        List<Product> actual = productService.findAllByPriceBetween(BigDecimal.valueOf(100), BigDecimal.valueOf(200), pageRequest, "title");
//        Assertions.assertEquals(1, actual.size());
//        Assertions.assertEquals(1L, actual.get(0).getId());
//        Assertions.assertEquals(BigDecimal.valueOf(147), actual.get(0).getPrice());
//        Assertions.assertEquals("tools", actual.get(0).getCategory().getName());
//        Assertions.assertEquals(1L, actual.get(0).getCategory().getId());
//    }

    @Test
    void getAllByCategoryIdIn() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("CrazyFrog");
        product.setPrice(BigDecimal.valueOf(15));
        Category category = new Category();
        category.setId(1L);
        category.setName("toys");
        product.setCategory(category);
        List<Long> categoriesId = new ArrayList<>();
        categoriesId.add(1L);
        Mockito.when(productRepository.findAllByCategoryIdIn(categoriesId)).thenReturn(List.of(product));
        List<Product> actual = productService.getAllByCategoryIdIn(categoriesId);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(1L, actual.get(0).getId());
        Assertions.assertEquals("toys", actual.get(0).getCategory().getName());
        Assertions.assertEquals(1L, actual.get(0).getCategory().getId());
    }
}