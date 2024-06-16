package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    Product findProductById(long id);

    Product save(Product product);

    Product findProductByIdIs(Long id);

    Product findProductByIdEquals(Long id);

    Product findByPriceBetweenAndTitleLessThan(double greaterThanEqualPrice, double lessThanEqualPrice, String title);

    Product findByPriceLessThanEqual(double price);

    List<Product> findByTitleIgnoreCaseStartingWith(String title);

    @Query("select p from Product p where p.id = :id and p.category.name = :categoryName")
    public List<Product> getByIdAndTitle(Long id, String categoryName);

}
