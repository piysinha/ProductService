package com.scaler.productservice.services;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class SelfProductService implements ProductService{

    private ProductService productService;

    public SelfProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
