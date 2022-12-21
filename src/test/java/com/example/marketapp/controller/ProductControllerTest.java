package com.example.marketapp.controller;

import com.example.marketapp.model.Category;
import com.example.marketapp.model.Product;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.util.ProductSortService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldShowProductByCategoriesId() {
        Category category = new Category();
        category.setId(1L);
        category.setName("phone");
        Product iPhone7 = new Product();
        iPhone7.setId(21L);
        iPhone7.setTitle("iPhone 7");
        iPhone7.setPrice(BigDecimal.valueOf(980));
        iPhone7.setCategory(category);
        Product iPhoneX = new Product();
        iPhoneX.setId(22L);
        iPhoneX.setTitle("iPhone X");
        iPhoneX.setPrice(BigDecimal.valueOf(1190));
        iPhoneX.setCategory(category);
        Product samsung20 = new Product();
        samsung20.setId(23L);
        samsung20.setTitle("Samsung S20");
        samsung20.setPrice(BigDecimal.valueOf(1250));
        samsung20.setCategory(category);
        List<Long> categoriesId = List.of(category.getId());
        List<Product> products = List.of(iPhone7, iPhoneX, samsung20);
        Mockito.when(productService.getAllByCategoryIdIn(categoriesId)).thenReturn(products);

        RestAssuredMockMvc
                .given()
                .queryParam("categoriesId", categoriesId)
                .when()
                .get("/products/categories")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(21))
                .body("[1].title", Matchers.equalTo("iPhone X"))
                .body("[2].price", Matchers.equalTo(1250))
                .body("[0].categoriesId", Matchers.equalTo(1));
    }

    @Test
    public void shouldShowProductByPriceBetween1000And2000() {
        Category category = new Category();
        category.setId(1L);
        category.setName("phone");
        Product iPhone7 = new Product();
        iPhone7.setId(21L);
        iPhone7.setTitle("iPhone 7");
        iPhone7.setPrice(BigDecimal.valueOf(980));
        iPhone7.setCategory(category);
        Product iPhoneX = new Product();
        iPhoneX.setId(22L);
        iPhoneX.setTitle("iPhone X");
        iPhoneX.setPrice(BigDecimal.valueOf(1190));
        iPhoneX.setCategory(category);
        Product samsung20 = new Product();
        samsung20.setId(23L);
        samsung20.setTitle("Samsung S20");
        samsung20.setPrice(BigDecimal.valueOf(1250));
        samsung20.setCategory(category);
        List<Product> products = List.of(iPhone7, iPhoneX, samsung20);
        BigDecimal from = BigDecimal.valueOf(1200);
        BigDecimal to = BigDecimal.valueOf(Integer.MAX_VALUE);
        String sortBy = "title";
        Sort sort = Sort.by(ProductSortService.getSort(sortBy));
        Pageable pageRequest = PageRequest.of(0, 1, sort);


        Mockito.when(productService.findAllByPriceBetween(from, to, pageRequest)).thenReturn(products);

        RestAssuredMockMvc
                .given()
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("count", 1)
                .queryParam("page", 0)
                .queryParam("sortBy", sortBy)
                .when()
                .get("/products/findAllByPrice")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[2].id", Matchers.equalTo(23))
                .body("[2].title", Matchers.equalTo("Samsung S20"))
                .body("[2].price", Matchers.equalTo(1250))
                .body("[2].categoriesId", Matchers.equalTo(1));

    }
}