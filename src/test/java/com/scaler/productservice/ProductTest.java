package com.scaler.productservice;

import com.scaler.productservice.clients.fakeStoreApi.FakeStoreClient;
import com.scaler.productservice.clients.fakeStoreApi.FakeStoreProductDto;
import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepositories;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepositories productRepositories;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void savingProductAndCategory(){
        Category category = new Category();
        category.setName("Mobile Phone");
        category.setDescription("This is a Phone Category");
        //categoryRepository.save(category);

        Product product = new Product();
        product.setDescription("This is a Phone");
        product.setCategory(category);
        product.setPrice(50000.00);
        product.setTitle("I Phone");
        product.setImageUrl("Iphone.com");
        productRepositories.save(product);
    }

    @Test
    @Transactional
    void fetchTypesTest(){
        Product product = productRepositories.findProductById(1L);
        System.out.println("Product name is : " + product.getTitle() + "Product price is : " + product.getPrice() +" "+ "Product Id is : " + product.getId());
        Category category = product.getCategory();
        System.out.println("Category name is : " + category.getName());

    }

    @Test
    void deleteProduct(){
        productRepositories.deleteById(2L);
    }

}
