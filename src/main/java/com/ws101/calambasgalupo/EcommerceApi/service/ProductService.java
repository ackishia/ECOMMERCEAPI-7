package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.dto.CreateProductDto;
import com.ws101.calambasgalupo.EcommerceApi.dto.ProductListingEntry;
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

    public ProductService(ProductRepository repo,
                          CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    // ===================== GET =====================

    // FULL PRODUCT RESPONSE
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // DTO RESPONSE (TASK 4)
    public List<ProductListingEntry> getProductListings() {

        return repo.findAll().stream()
                .map(product -> new ProductListingEntry(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ))
                .toList();
    }

    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // ===================== CREATE =====================

    public Product addProduct(CreateProductDto dto) {

        Product product = new Product();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStockQuantity(dto.stockQuantity());
        product.setImageUrl(dto.imageUrl());

        // SET CATEGORY
        if (dto.categoryId() != null) {

            Category category = categoryRepo.findById(dto.categoryId())
                    .orElseThrow(() ->
                            new RuntimeException("Category not found"));

            product.setCategory(category);
        }

        return repo.save(product);
    }

    // ===================== UPDATE =====================

    public Product updateProduct(Long id,
                                 CreateProductDto dto) {

        Product existing = getProductById(id);

        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setStockQuantity(dto.stockQuantity());
        existing.setImageUrl(dto.imageUrl());

        // UPDATE CATEGORY
        if (dto.categoryId() != null) {

            Category category = categoryRepo.findById(dto.categoryId())
                    .orElseThrow(() ->
                            new RuntimeException("Category not found"));

            existing.setCategory(category);
        }

        return repo.save(existing);
    }

    // ===================== PATCH =====================

    public Product patchProduct(Long id,
                                Map<String, Object> updates) {

        Product product = getProductById(id);

        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }

        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("price")) {
            product.setPrice(
                    ((Number) updates.get("price")).doubleValue()
            );
        }

        if (updates.containsKey("category")) {

            Map<String, Object> categoryMap =
                    (Map<String, Object>) updates.get("category");

            Long categoryId =
                    Long.valueOf(categoryMap.get("id").toString());

            Category category = categoryRepo.findById(categoryId)
                    .orElseThrow(() ->
                            new RuntimeException("Category not found"));

            product.setCategory(category);
        }

        if (updates.containsKey("stockQuantity")) {
            product.setStockQuantity(
                    ((Number) updates.get("stockQuantity")).intValue()
            );
        }

        if (updates.containsKey("imageUrl")) {
            product.setImageUrl(
                    (String) updates.get("imageUrl")
            );
        }

        return repo.save(product);
    }

    // ===================== DELETE =====================

    public void deleteProduct(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        repo.deleteById(id);
    }

    // ===================== FILTER =====================

    public List<Product> filterProducts(String filterType,
                                        String filterValue) {

        switch (filterType.toLowerCase()) {

            case "name":
                return repo.findAll().stream()
                        .filter(p ->
                                p.getName() != null &&
                                        p.getName().equalsIgnoreCase(filterValue))
                        .toList();

            case "category":
                return repo.findByCategoryName(filterValue);

            case "price":

                try {

                    double price =
                            Double.parseDouble(filterValue);

                    return repo.findAll().stream()
                            .filter(p -> p.getPrice() == price)
                            .toList();

                } catch (NumberFormatException e) {

                    throw new IllegalArgumentException(
                            "Invalid price value"
                    );
                }

            default:
                return List.of();
        }
    }

    // ===================== PRICE RANGE =====================

    public List<Product> filterProductWithPrice(
            double minPrice,
            double maxPrice) {

        if (minPrice < 0 ||
                maxPrice < 0 ||
                minPrice > maxPrice) {

            throw new IllegalArgumentException(
                    "Invalid price range"
            );
        }

        return repo.findByPriceRange(minPrice, maxPrice);
    }
}