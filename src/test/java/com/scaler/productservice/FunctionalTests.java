package com.scaler.productservice;

import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.repositories.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(ProductController.class)
//public class FunctionalTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ProductController productController;

//    @Test
//    void testGetAllProducts() throws Exception {
//        mockMvc.perform(get("/products")).andExpect(status().is(200));
//    }
//}
