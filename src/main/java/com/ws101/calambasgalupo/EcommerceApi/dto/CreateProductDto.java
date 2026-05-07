package com.ws101.calambasgalupo.EcommerceApi.dto;

import jakarta.validation.constraints.*;

public record CreateProductDto(

        @NotBlank(message = "Product name is required")
        @Size(min = 2, max = 100)
        String name,

        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        @NotNull(message = "Stock quantity is required")
        @PositiveOrZero(message = "Stock cannot be negative")
        Integer stockQuantity,

        String imageUrl,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {}
