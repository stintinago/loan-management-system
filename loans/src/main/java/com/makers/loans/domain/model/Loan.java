package com.makers.loans.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data // Generate Getters, Setters, toString, equals y hashCode
@AllArgsConstructor // Generates the constructor with all fields
@NoArgsConstructor // Generates an empty constructor
public class Loan {
    private Long id;
    private Double amount;
    private Integer term;
    private String status;
    private Long userId;
    private String userEmail;
    private LocalDateTime createdAt = LocalDateTime.now();
}