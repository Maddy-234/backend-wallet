package com.payment.controller;

import com.payment.model.User;
import com.payment.request.AuthRequest;
import com.payment.response.AuthResponse;
import com.payment.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.login(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok(new AuthResponse("Login Success",token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        // Register the user and generate the token
        String token = authService.registerUser(user);
        return ResponseEntity.ok(new AuthResponse("Register Successfully",token));  // Return the token in the response
    }
}



