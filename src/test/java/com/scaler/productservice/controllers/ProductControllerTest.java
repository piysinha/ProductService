package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import com.scaler.productservice.services.SelfProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

//@SpringBootTest
class ProductControllerTest {
//    @Autowired
//    private ProductController productController;
//
//    @MockBean
//    private SelfProductService productService;
//
//    @Test
//    void getAllProductReturnEmptyList() {
//        List<Product> p = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setPrice(109.95);
//        p.add(p1);

//        when(productService.getAllProducts()).thenReturn(p);
//        List<Product> products = productController.getAllProducts();
//        assertEquals(109.95, products.get(0).getPrice());
        //assertTrue(products.get(0).getPrice() == 109.95); //Returning Boolean
        //assert products.get(0).getPrice() == 109.95; //Normal Assert does not show values when failed

        //1. Using the AssertJ libraries to carry the tests in more readable manner.
        // a. We will first pick the date that we want to validate.
        // b. Call the different validations that you want to do.
//        assertThat(products.get(0).getPrice())
//                .isEqualTo(109.95)
//                .isGreaterThan(100)
//                .isLessThan(100);
//    }
//
}