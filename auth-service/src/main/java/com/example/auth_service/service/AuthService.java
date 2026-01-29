package com.example.auth_service.service;

import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.SignupRequest;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse signup(SignupRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse(false, "Username already exists", null, null);
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(false, "Email already exists", null, null);
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(true, "User registered successfully", token, request.getUsername());
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (!user.isPresent()) {
            return new AuthResponse(false, "User not found", null, null);
        }

        if (!user.get().isEnabled()) {
            return new AuthResponse(false, "User account is disabled", null, null);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return new AuthResponse(false, "Invalid password", null, null);
        }

        // Generate token
        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(true, "Login successful", token, request.getUsername());
    }

    public AuthResponse logout(String token) {
        if (jwtUtil.validateToken(token)) {
            return new AuthResponse(true, "Logout successful", null, null);
        }
        return new AuthResponse(false, "Invalid token", null, null);
    }

    public AuthResponse validateToken(String token) {
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            return new AuthResponse(true, "Token is valid", token, username);
        }
        return new AuthResponse(false, "Invalid token", null, null);
    }
}
