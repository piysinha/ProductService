package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.ProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepositories;
import com.scaler.productservice.services.CategoryService;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

/*This is a rest controller*/
@RestController("")
@RequestMapping("/products")
public class ProductController{

    private ProductService productService;
    private ProductRepositories productRepositories;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, ProductRepositories productRepositories) {
        this.productService = productService;
        this.productRepositories = productRepositories;
    }

    @GetMapping()
    //Controller is nothing but a set of methods
    public List<Product> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        //products.get(0).setPrice(100);
        return products;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(
                "auth_token","noaccesskeyrequestfound"
        );

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()){
            throw new NotFoundException("No product with product id: "+ productId);
        }
        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                OK
        );
        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
//        Product newProduct = productService.addNewProduct(
//                productDto
//        );
        Product newProduct = new Product();
        newProduct.setDescription(productDto.getDescription());
        newProduct.setImageUrl(productDto.getImage());
        newProduct.setTitle(productDto.getTitle());
        newProduct.setPrice(productDto.getPrice());

        newProduct = productRepositories.save(newProduct);

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }
    /*Updating a product*/
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody ProductDto productDto){

        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Product";
    }


}