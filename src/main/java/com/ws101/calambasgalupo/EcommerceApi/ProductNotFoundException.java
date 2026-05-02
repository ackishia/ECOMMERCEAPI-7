package com.ws101.calambasgalupo.EcommerceApi;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}