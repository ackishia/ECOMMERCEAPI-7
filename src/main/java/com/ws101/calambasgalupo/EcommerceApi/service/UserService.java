package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.dto.RegisterUserDto;
import com.ws101.calambasgalupo.EcommerceApi.model.User;
import com.ws101.calambasgalupo.EcommerceApi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterUserDto dto) {

        User user = new User();

        user.setUsername(dto.username());

        // ENCODE PASSWORD
        user.setPassword(
                passwordEncoder.encode(dto.password())
        );

        // ROLE FORMAT
        String role = dto.role();

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        user.setRole(role);

        return userRepository.save(user);
    }
}
