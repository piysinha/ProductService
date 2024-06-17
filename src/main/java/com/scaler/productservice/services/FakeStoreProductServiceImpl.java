package com.scaler.productservice.services;

import com.scaler.productservice.clients.fakeStoreApi.FakeStoreClient;
import com.scaler.productservice.clients.fakeStoreApi.FakeStoreProductDto;
import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
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
        product.setImageUrl(fakeStoreProductDto.getImageUrl());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto productDto : fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDtos = fakeStoreClient.getSingleProduct(productId);
        if(fakeStoreProductDtos == null){
            return Optional.empty();
        }
        return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDtos));
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto fakeStoreProductDtos = response.getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtos);
    }



    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDtos = fakeStoreClient.updateProduct
                                                    (productId, product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtos);
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
