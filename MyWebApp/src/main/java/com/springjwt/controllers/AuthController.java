package com.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springjwt.entities.User;
import com.springjwt.repositories.UserRepository;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*") // Allow all for testing
@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(user -> email.equals(user.getEmail()) && password.equals(user.getPassword()))
                .findFirst();

        if (userOptional.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", "mock-token-" + email); // You can use JWT here
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

