package com.ws101.calambasgalupo.EcommerceApi.controller;

import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import com.ws101.calambasgalupo.EcommerceApi.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ===================== GET =====================

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    // ===================== FILTER =====================

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(
                service.filterProducts(filterType, filterValue)
        );
    }

    // ===================== PRICE RANGE =====================

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> filterByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return ResponseEntity.ok(
                service.filterProductWithPrice(minPrice, maxPrice)
        );
    }

    // ===================== CREATE =====================

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category ID is required");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addProduct(product));
    }

    // ===================== UPDATE =====================

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category ID is required");
        }

        return ResponseEntity.ok(service.updateProduct(id, product));
    }

    // ===================== PATCH =====================

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(service.patchProduct(id, updates));
    }

    // ===================== DELETE =====================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}