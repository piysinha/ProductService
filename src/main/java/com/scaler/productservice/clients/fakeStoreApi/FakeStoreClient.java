package com.scaler.productservice.clients.fakeStoreApi;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return Arrays.asList(productList.getBody());
    }

    public Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);

        return Optional.of(productList.getBody());
    }

    FakeStoreProductDto addNewProduct(ProductDto productDto){
        return null;
    }

    /*Patch method*/
    FakeStoreProductDto updateProduct(Long productId, Product product){
        return null;
    }

    /*Put Method*/
    FakeStoreProductDto replaceProduct(Long productId, Product product){
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
