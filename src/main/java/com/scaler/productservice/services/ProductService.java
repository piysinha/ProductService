package com.scaler.productservice.services;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto productDto);

    Product updateProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}
