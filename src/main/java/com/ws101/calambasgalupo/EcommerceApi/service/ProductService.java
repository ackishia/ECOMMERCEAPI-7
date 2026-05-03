package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.model.Category;
import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import com.ws101.calambasgalupo.EcommerceApi.repository.CategoryRepository;
import com.ws101.calambasgalupo.EcommerceApi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    // GET all products
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // GET by ID
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // CREATE
    public Product addProduct(Product product) {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepo.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        return repo.save(product);
    }

    // UPDATE (PUT)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());

        if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getId() != null) {
            Category category = categoryRepo.findById(updatedProduct.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }

        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return repo.save(existing);
    }

    // PATCH
    public Product patchProduct(Long id, Map<String, Object> updates) {
        Product product = getProductById(id);

        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }

        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("price")) {
            product.setPrice(((Number) updates.get("price")).doubleValue());
        }

        if (updates.containsKey("category")) {
            Map<String, Object> categoryMap = (Map<String, Object>) updates.get("category");
            Long categoryId = Long.valueOf(categoryMap.get("id").toString());

            Category category = categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            product.setCategory(category);
        }

        if (updates.containsKey("stockQuantity")) {
            product.setStockQuantity(((Number) updates.get("stockQuantity")).intValue());
        }

        if (updates.containsKey("imageUrl")) {
            product.setImageUrl((String) updates.get("imageUrl"));
        }

        return repo.save(product);
    }

    // DELETE
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    // FILTER
    public List<Product> filterProducts(String filterType, String filterValue) {

        switch (filterType.toLowerCase()) {
            case "name":
                return repo.findAll().stream()
                        .filter(p -> p.getName().equalsIgnoreCase(filterValue))
                        .toList();

            case "category":
                return repo.findByCategoryName(filterValue);

            case "price":
                return repo.findAll().stream()
                        .filter(p -> p.getPrice() == Double.parseDouble(filterValue))
                        .toList();

            default:
                return List.of();
        }
    }

    // FILTER by price range
    public List<Product> filterProductWithPrice(double minPrice, double maxPrice) {

        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new IllegalArgumentException("Invalid price range");
        }

        return repo.findByPriceRange(minPrice, maxPrice);
    }
}