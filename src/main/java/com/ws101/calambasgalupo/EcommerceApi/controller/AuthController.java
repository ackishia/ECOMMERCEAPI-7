package com.ws101.calambasgalupo.EcommerceApi.controller;

import com.ws101.calambasgalupo.EcommerceApi.model.User;
import com.ws101.calambasgalupo.EcommerceApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Added import
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    // Constructor injection
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // @Valid triggers validation BEFORE data reaches the service layer
    // PUBLIC ENDPOINT: No @PreAuthorize needed = Accessible to everyone
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

}
