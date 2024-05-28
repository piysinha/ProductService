package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.
                requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    /*Creating a common method which will convert the fakeStoreProductDto to product DTO
    * instead of converting the product dto to Product in every method*/
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto[]> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

//            Product product = new Product();
//            product.setId(fakeStoreProductDto.getId());
//            product.setTitle(fakeStoreProductDto.getTitle());
//            product.setPrice(fakeStoreProductDto.getPrice());
//            product.setDescription(fakeStoreProductDto.getDescription());
//            Category category = new Category();
//            category.setName(fakeStoreProductDto.getCategory());
//            product.setCategory(category);
//            product.setImageUrl(fakeStoreProductDto.getImage());
        for (FakeStoreProductDto fakeStoreProductDto : productList.getBody()) {
            answer.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> productList= restTemplate.getForEntity(
                "http://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);
        FakeStoreProductDto fakeStoreProductDto = productList.getBody();
//        Product product = new Product();
//        product.setId(fakeStoreProductDto.getId());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        Category category = new Category();
//        category.setName(fakeStoreProductDto.getCategory());
//        product.setCategory(category);
//        product.setImageUrl(fakeStoreProductDto.getImage());
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductDto> productList = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto,
                FakeStoreProductDto.class
        );
//        ProductDto productDto1 = productList.getBody();
//        Product product1 = new Product();
//        product1.setId(productDto1.getId());
//        product1.setTitle(productDto1.getTitle());
//        product1.setPrice(productDto1.getPrice());
//        product1.setDescription(productDto1.getDescription());
//        Category category = new Category();
//        category.setName(productDto1.getCategory());
//        product1.setCategory(category);
//        product1.setImageUrl(productDto1.getImage());
        FakeStoreProductDto fakeStoreProductDto = productList.getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }



    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
