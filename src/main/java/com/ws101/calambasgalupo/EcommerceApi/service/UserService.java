package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.model.User;       // Import your User model
import com.ws101.calambasgalupo.EcommerceApi.repository.UserRepository; // Import your User Repository
import org.springframework.security.crypto.password.PasswordEncoder; // Import Password Encoder
import org.springframework.stereotype.Service; // Marks this class as a Spring Service

@Service // Tells Spring to manage this class as a bean
public class UserService {

    // Inject dependencies using constructor injection (best practice)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor - Spring will automatically provide these objects
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user:
     * 1. Encode the password (hash it) before saving to database
     * 2. Save the user data using UserRepository
     */
    public User registerUser(User user) {
        // ✅ Encode the plain password into a secure hash
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // ✅ Save the user to the database
        return userRepository.save(user);
    }
}

