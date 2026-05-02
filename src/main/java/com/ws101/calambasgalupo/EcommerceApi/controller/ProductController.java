package com.ws101.calambasgalupo.EcommerceApi.controller;

import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import com.ws101.calambasgalupo.EcommerceApi.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for handling product-related API requests.
 * Provides endpoints for CRUD operations and filtering products.
 *
 *  @author Jackielyn & Chris
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    /**
     * Constructor for ProductController.
     * @param service ProductService instance
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Retrieves all products.
     * @return list of all products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    /**
     * Retrieves a single product by its ID.
     * @param id product ID
     * @return product if found, otherwise 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getProductById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Filters products based on type and value.
     * @param filterType criteria (e.g., name, category, price)
     * @param filterValue value to filter by
     * @return list of filtered products
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(
                service.filterProducts(filterType, filterValue)
        );
    }

    /**
     * Creates a new product.
     * @param product product data from request body
     * @return created product with status 201
     */
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addProduct(product));
    }

    /**
     * Updates an existing product completely.
     * @param id product ID
     * @param product updated product data
     * @return updated product or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        try {
            return ResponseEntity.ok(service.updateProduct(id, product));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Partially updates a product.
     * @param id product ID
     * @param updates map of fields to update
     * @return updated product or 404 if not found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        try {
            return ResponseEntity.ok(service.patchProduct(id, updates));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a product by ID.
     * @param id product ID
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}