package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.repositories.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SelfProductServiceTest {
    @MockBean
    private ProductRepositories productRepositories;

    @Autowired
    private SelfProductService selfProductService;


    @Test
    void testSingleProductThrowsExceptionWhenProductDoesNotExist() {
        when(productRepositories.findProductById(anyInt())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> selfProductService.getSingleProduct(1L));
    }
}