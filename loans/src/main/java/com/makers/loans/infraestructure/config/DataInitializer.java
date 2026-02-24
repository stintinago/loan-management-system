package com.makers.loans.infraestructure.config;

import com.makers.loans.infraestructure.adapters.output.persistence.entity.UserEntity;
import com.makers.loans.infraestructure.adapters.output.persistence.repository.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Creates an admin if it does not exist
            if (userRepository.findByEmail("admin@test.com").isEmpty()) {
                userRepository.save(new UserEntity(null, "admin@test.com",
                        passwordEncoder.encode("123"), "ROLE_ADMIN"));
                System.out.println("Admin creado: admin@test.com / 123");
            }
            // Creates and user if it does not exist
            if (userRepository.findByEmail("usuario@test.com").isEmpty()) {
                userRepository.save(new UserEntity(null, "usuario@test.com",
                        passwordEncoder.encode("123"), "ROLE_USER"));
                System.out.println("Usuario creado: usuario@test.com / 123");
            }
        };
    }
}