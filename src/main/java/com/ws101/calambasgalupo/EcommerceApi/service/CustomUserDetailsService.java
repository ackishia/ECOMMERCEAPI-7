package com.ws101.calambasgalupo.EcommerceApi.service;

// Import your User model from the correct package
import com.ws101.calambasgalupo.EcommerceApi.model.User;
import com.ws101.calambasgalupo.EcommerceApi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    // Constructor-based injection (Spring recommended)
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from database using UserRepository
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return the User object - since User implements UserDetails, this is valid
        return user;
    }
}