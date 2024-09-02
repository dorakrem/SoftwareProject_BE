package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.JwtResponse;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        System.out.println("Received login request for user: " + user.getLogin()); // Debug print

        Optional<User> existingUserOptional = authenticationService.findUserByLogin(user.getLogin());

        if (existingUserOptional.isPresent()) {
            System.out.println("User found in database"); // Debug print

            User existingUser = existingUserOptional.get();
            System.out.println("Stored hashed password: " + existingUser.getPassword()); // Debug print

            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                System.out.println("Password matches, generating JWT token"); // Debug print
                // Create and return JWT token
                String token = jwtUtil.generateToken(existingUser.getLogin());
                return ResponseEntity.ok().body(new JwtResponse(token));
            } else {
                System.out.println("Password does not match"); // Debug print
            }
        } else {
            System.out.println("User not found"); // Debug print
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
