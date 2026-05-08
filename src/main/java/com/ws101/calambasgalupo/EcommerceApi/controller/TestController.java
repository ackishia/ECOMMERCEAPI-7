package com.ws101.calambasgalupo.EcommerceApi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/v1/auth/me")
    public Authentication getUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/api/v1/admin/test")
    public String adminTest() {
        return "Welcome Admin!";
    }
}