package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto[]> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto FakeStoreProductDto : productList.getBody()) {
            Product product = new Product();
            product.setId(FakeStoreProductDto.getId());
            product.setTitle(FakeStoreProductDto.getTitle());
            product.setPrice(FakeStoreProductDto.getPrice());
            product.setDescription(FakeStoreProductDto.getDescription());
            Category category = new Category();
            category.setName(FakeStoreProductDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(FakeStoreProductDto.getImage());

            answer.add(product);
        }
        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);
        FakeStoreProductDto FakeStoreProductDto = productList.getBody();
        Product product = new Product();
        product.setId(FakeStoreProductDto.getId());
        product.setTitle(FakeStoreProductDto.getTitle());
        product.setPrice(FakeStoreProductDto.getPrice());
        product.setDescription(FakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(FakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(FakeStoreProductDto.getImage());

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <ProductDto> productList = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto,
                ProductDto.class
        );
        ProductDto productDto1 = productList.getBody();
        Product product1 = new Product();
        product1.setId(productDto1.getId());
        product1.setTitle(productDto1.getTitle());
        product1.setPrice(productDto1.getPrice());
        product1.setDescription(productDto1.getDescription());
        Category category = new Category();
        category.setName(productDto1.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto1.getImage());


        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
