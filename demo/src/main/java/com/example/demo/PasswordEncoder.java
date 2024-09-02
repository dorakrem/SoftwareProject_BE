package com.example.demo; // Adjust the package name according to your project structure

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "jdk10"; // This should be the raw password you want to hash
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        // Check if the raw password matches the encoded password
        boolean isMatch = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + isMatch);
    }
}
