package com.scaler.productservice.clients.fakeStoreApi;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    public FakeStoreProductDto getSingleProduct(Long productId) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);

        return productList.getBody();
    }
    //Post Method
    public FakeStoreProductDto addNewProduct(ProductDto product){
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    /*Patch method*/
    public FakeStoreProductDto updateProduct(Long productId, Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        //returning Object below method is returning an object
//        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.patchForObject(
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//        );
        //Below method returning an entity
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return fakeStoreProductDtoResponseEntity.getBody();
    }

    /*Put Method*/
    FakeStoreProductDto replaceProduct(Long productId, Product product){
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
