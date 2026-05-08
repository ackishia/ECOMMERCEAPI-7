package com.ws101.calambasgalupo.EcommerceApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(

        @NotBlank(message = "Username is required")
        @Size(min = 8, max = 20,
                message = "Username must be between 8 and 20 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6,
                message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Role is required")
        String role
) {
}