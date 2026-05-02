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

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getProductById(id));
        } catch (Exception e) { //  changed (more general, safer)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // FILTER
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(
                service.filterProducts(filterType, filterValue)
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addProduct(product));
    }

    // PUT (FULL UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        try {
            return ResponseEntity.ok(service.updateProduct(id, product));
        } catch (Exception e) { //  changed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // PATCH (PARTIAL UPDATE)
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        try {
            return ResponseEntity.ok(service.patchProduct(id, updates));
        } catch (Exception e) { //  changed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) { //  changed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}