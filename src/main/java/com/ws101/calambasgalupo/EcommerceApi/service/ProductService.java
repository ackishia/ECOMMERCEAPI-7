package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import com.ws101.calambasgalupo.EcommerceApi.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Service class for product operations.
 */
@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ProductService() {
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product createProduct(Product product) {
        product.setId(counter.incrementAndGet());
        productList.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setImageUrl(updated.getImageUrl());

        return existing;
    }

    public Product patchProduct(Long id, Map<String, Object> updates) {
        Product product = getProductById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> product.setName((String) value);
                case "price" -> product.setPrice(Double.parseDouble(value.toString()));
                case "category" -> product.setCategory((String) value);
                case "stockQuantity" -> product.setStockQuantity((Integer) value);
            }
        });

        return product;
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productList.remove(product);
    }

    public List<Product> filter(String type, String value) {
        return switch (type.toLowerCase()) {
            case "name" -> productList.stream()
                    .filter(p -> p.getName().toLowerCase().contains(value.toLowerCase()))
                    .collect(Collectors.toList());

            case "category" -> productList.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(value))
                    .collect(Collectors.toList());

            case "price" -> {
                double price = Double.parseDouble(value);
                yield productList.stream()
                        .filter(p -> p.getPrice() == price)
                        .collect(Collectors.toList());
            }

            default -> new ArrayList<>();
        };
    }
}