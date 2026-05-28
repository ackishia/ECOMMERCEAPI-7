package com.ws101.calambasgalupo.EcommerceApi.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(
            PasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (username.equals("admin")) {

            return User.builder()
                    .username("admin")
                    .password(
                            passwordEncoder.encode("1234")
                    )
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException(
                "User not found"
        );
    }
}