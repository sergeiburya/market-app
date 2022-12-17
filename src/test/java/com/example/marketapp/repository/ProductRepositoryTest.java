package com.example.marketapp.repository;

import com.example.marketapp.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Container
    static MySQLContainer<?> database = new MySQLContainer<>("mysql:8")
            .withDatabaseName("springboot")
            .withPassword("springboot")
            .withUsername("springboot");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql("/scripts/init-test-data.sql")
    void shouldReturnProductPriceGreaterThan1000() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.ASC);
        List<Product> actual = productRepository.findAllByPriceBetween(BigDecimal.valueOf(1000),
                BigDecimal.valueOf(Integer.MAX_VALUE), pageRequest);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(1, actual.get(0).getId());
        Assertions.assertEquals("Samsung X9", actual.get(0).getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(1200), actual.get(0).getPrice());
        Assertions.assertEquals(1, actual.get(0).getCategory().getId());
    }
}