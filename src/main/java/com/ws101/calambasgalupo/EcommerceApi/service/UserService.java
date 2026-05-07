package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.dto.RegisterUserDto; // Import your DTO
import com.ws101.calambasgalupo.EcommerceApi.model.User;
import com.ws101.calambasgalupo.EcommerceApi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Changed parameter from User to RegisterUserDto
    public User registerUser(RegisterUserDto dto) {

        // 1. Create a new User object (Entity)
        User user = new User();

        // 2. Copy data from DTO to Entity
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role()); // kung may role ka sa DTO

        // 3. Encode the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // 4. Save to database
        return userRepository.save(user);
    }
}

