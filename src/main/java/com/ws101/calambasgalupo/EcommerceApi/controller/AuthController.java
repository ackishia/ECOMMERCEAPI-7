package com.ws101.calambasgalupo.EcommerceApi.controller;
import com.ws101.calambasgalupo.EcommerceApi.dto.RegisterUserDto; // Import your DTO
import com.ws101.calambasgalupo.EcommerceApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserDto dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully!");
    }

}