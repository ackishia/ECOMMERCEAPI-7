package com.ws101.calambasgalupo.EcommerceApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be 3–20 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be at least 8 characters")
        String password,

        @NotBlank(message = "Role is required")
        @Pattern(regexp = "ROLE_(ADMIN|USER|SELLER)", message = "Role must be ROLE_ADMIN, ROLE_USER, or ROLE_SELLER")
        String role
) {}