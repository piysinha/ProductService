package com.scaler.productservice.services;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    Page<Product> getProducts(int noOfProducts, int offSet);

    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto productDto);


    /*Patch method*/
    Product updateProduct(Long productId, Product product);

    /*Put Method*/
    Product replaceProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}
