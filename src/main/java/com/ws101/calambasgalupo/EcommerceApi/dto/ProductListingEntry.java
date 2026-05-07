package com.ws101.calambasgalupo.EcommerceApi.dto;

// This one is just for displaying data, usually no validation needed here
public record ProductListingEntry(
        Long prodId,
        String prodName,
        double prodPrice
) {}