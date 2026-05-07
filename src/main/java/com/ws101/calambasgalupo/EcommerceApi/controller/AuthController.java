package com.ws101.calambasgalupo.EcommerceApi.controller;

import com.ws101.calambasgalupo.EcommerceApi.model.User;
import com.ws101.calambasgalupo.EcommerceApi.service.UserService;
import jakarta.validation.Valid; // ✅ Missing import added
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth") // Base path for authentication
public class AuthController {

    private final UserService userService;

    // Constructor injection
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Public Endpoint: Register new user
    // @Valid triggers validation BEFORE data reaches the service layer
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

} // ✅ Missing closing bracket added

