package com.ws101.calambasgalupo.EcommerceApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    // User-specific endpoint: Requires authentication
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> request) {

        // simulate extracting values
        Object productId = request.get("productId");
        Object quantity = request.get("quantity");

        if (productId == null || quantity == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "productId and quantity are required"));
        }

        // fake response (for now)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order created successfully");
        response.put("productId", productId);
        response.put("quantity", quantity);

        return ResponseEntity.ok(response);
    }
}

