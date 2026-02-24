package com.makers.loans.infraestructure.adapters.input.rest;

import com.makers.loans.infraestructure.adapters.output.persistence.entity.UserEntity;
import com.makers.loans.infraestructure.adapters.output.persistence.repository.JpaUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> {
                    // Simple response
                    Map<String, String> response = new java.util.HashMap<>();
                    response.put("email", user.getEmail());
                    response.put("role", user.getRole());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(401).build());
    }
}