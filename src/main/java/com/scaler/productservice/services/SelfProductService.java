package com.scaler.productservice.services;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepositories;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private final ProductRepositories productRepositories;
    private ProductService productService;

    public SelfProductService(ProductService productService, ProductRepositories productRepositories) {
        this.productService = productService;
        this.productRepositories = productRepositories;
    }


    public Page<Product> getProducts(int noOfProducts, int offSet) {
        Page<Product>products = productRepositories.findAll(
                PageRequest.of((offSet/noOfProducts),
                        noOfProducts,
                        Sort.by("price").descending()
                                .and(Sort.by("title")
                                        .descending()))
        );
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = productRepositories.findProductById(productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return Optional.of(product);
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
