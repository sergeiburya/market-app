//package com.example.marketapp.repository;
//
//import com.example.marketapp.model.Product;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@DataJpaTest
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class ProductRepositoryTest {
//    @Container
//    static MySQLContainer<?> database = new MySQLContainer<>("mysql:8")
//            .withDatabaseName("springboot")
//            .withPassword("springboot")
//            .withUsername("springboot");
//
//    @DynamicPropertySource
//    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
//        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
//        propertyRegistry.add("spring.datasource.password", database::getPassword);
//        propertyRegistry.add("spring.datasource.username", database::getUsername);
//    }
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    @Sql("/scripts/init-test-data.sql")
//    void shouldReturnProductPriceGreaterThan1800() {
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("price"));
//        List<Product> actual = productRepository.findAllByPriceBetween(BigDecimal.valueOf(1800),
//                BigDecimal.valueOf(Integer.MAX_VALUE), pageRequest);
//        Assertions.assertEquals(1, actual.size());
//        Assertions.assertEquals(31, actual.get(31).getId());
//        Assertions.assertEquals("Samsung X5 gold", actual.get(31).getTitle());
//        Assertions.assertEquals(BigDecimal.valueOf(1876), actual.get(31).getPrice());
//        Assertions.assertEquals(1, actual.get(0).getCategory().getId());
//    }
//
//    @Test
//    @Sql("/scripts/init-test-data.sql")
//    void shouldReturnProductPriceGreaterThan100AndLessThan300() {
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("title"));
//        List<Product> actual = productRepository.findAllByPriceBetween(BigDecimal.valueOf(100),
//                BigDecimal.valueOf(300), pageRequest);
//        Assertions.assertEquals(3, actual.size());
//        Assertions.assertEquals(2, actual.get(1).getId());
//        Assertions.assertEquals("Huawei 9S", actual.get(1).getTitle());
//        Assertions.assertEquals(BigDecimal.valueOf(260), actual.get(1).getPrice());
//        Assertions.assertEquals(1, actual.get(1).getCategory().getId());
//    }
//
//    @Test
//    @Sql("/scripts/init-test-data.sql")
//    void shouldReturnProductByCategoriesIdIn1And3() {
//        List<Long> categoriesId = new ArrayList<>();
//        categoriesId.add(0,1L);
//        categoriesId.add(1, 3L);
//        List<Product> actual = productRepository.findAllByCategoryIdIn(categoriesId);
//        Assertions.assertEquals(6, actual.size());
//        Assertions.assertEquals(1, actual.get(0).getId());
//        Assertions.assertEquals(2, actual.get(1).getId());
//        Assertions.assertEquals(3, actual.get(2).getId());
//        Assertions.assertEquals(7, actual.get(6).getId());
//        Assertions.assertEquals(8, actual.get(7).getId());
//        Assertions.assertEquals(9, actual.get(8).getId());
//        Assertions.assertEquals("Drill", actual.get(6).getTitle());
//        Assertions.assertEquals(BigDecimal.valueOf(18), actual.get(8).getPrice());
//    }
//}
