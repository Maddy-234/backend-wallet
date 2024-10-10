package com.payment.service.impl;

import com.payment.config.JwtUtil;
import com.payment.model.User;
import com.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Method to register a user and generate a JWT token
    public String registerUser(User user) {
        // Encode the password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        // Save the user in the database
        User savedUser = userRepository.save(user);

        // Generate JWT token for the registered user
        return jwtUtil.generateToken(savedUser.getUsername());
    }

    // Method to authenticate user and generate a JWT token during login
    public String login(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Generate JWT token for valid login
        return jwtUtil.generateToken(username);
    }
}
