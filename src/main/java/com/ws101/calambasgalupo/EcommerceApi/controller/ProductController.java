package com.ws101.calambasgalupo.EcommerceApi.controller;

import com.ws101.calambasgalupo.EcommerceApi.dto.CreateProductDto;
import com.ws101.calambasgalupo.EcommerceApi.dto.ProductListingEntry;
import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import com.ws101.calambasgalupo.EcommerceApi.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ===================== GET ALL (DTO RESPONSE) =====================
    @GetMapping
    public ResponseEntity<List<ProductListingEntry>> getAll() {

        return ResponseEntity.ok(
                service.getProductListings()
        );
    }

    // ===================== GET BY ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getProductById(id)
        );
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<Product> create(
            @Valid @RequestBody CreateProductDto dto) {

        Product saved = service.addProduct(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // ===================== UPDATE =====================
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody CreateProductDto dto) {

        Product updated = service.updateProduct(id, dto);

        return ResponseEntity.ok(updated);
    }

    // ===================== PATCH =====================
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                service.patchProduct(id, updates)
        );
    }

    // ===================== DELETE =====================
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}