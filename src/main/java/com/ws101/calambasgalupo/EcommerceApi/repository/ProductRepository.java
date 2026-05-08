package com.ws101.calambasgalupo.EcommerceApi.repository;

import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find by category name
    List<Product> findByCategoryName(String name);

    // Custom query: price range (your original)
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceRange(@Param("min") double min,
                                   @Param("max") double max);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}