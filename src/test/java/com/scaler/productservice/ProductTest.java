package com.scaler.productservice;

import com.scaler.productservice.clients.fakeStoreApi.FakeStoreClient;
import com.scaler.productservice.clients.fakeStoreApi.FakeStoreProductDto;
import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepositories;
import com.scaler.productservice.services.ProductService;
import com.scaler.productservice.services.SelfProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepositories productRepositories;

    @Autowired
    private CategoryRepository categoryRepository;
    @Qualifier("productService")
    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    @Rollback(value = false)
    void savingProductAndCategory(){
        Category category = categoryRepository.findById(102L).get();
//        category.setName("Laptop");
//        category.setDescription("This is a Laptop Category");
//        categoryRepository.save(category);

        Product product = new Product();
        product.setDescription("This is a Laptop");
        product.setCategory(category);
        product.setPrice(150000.00);
        product.setTitle("I Mac");
        product.setImageUrl("Apple.com");
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
    @Transactional
    @Rollback(false)
    void saveProductForCategory(){
        Category category = categoryRepository.findById(52l).get();
        Product product = new Product();
        product.setDescription("This is a Phone");
        product.setCategory(category);
        product.setPrice(95000.00);
        product.setTitle("Samsung");
        product.setImageUrl("Samsung.com");

        productRepositories.save(product);
    }

    @Test
    @Transactional
    void getProductsForCategory(){
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println(product.getTitle()+" "+product.getPrice()+" "+category.getName());
            }
        }
    }

    @Test
    void deleteProduct(){
        productRepositories.deleteById(2L);
    }

//    @Test
//    void checkWorkingFine() throws NotFoundException {
//        SelfProductService selfProductService = new SelfProductService(productService);
//        selfProductService.getSingleProduct(1L);
//    }

}
