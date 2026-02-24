package com.makers.loans.infraestructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto no puede ser nulo")
    @Min(value = 1, message = "El monto debe ser al menos 1")
    private Double amount;

    @NotNull(message = "El plazo (term) es obligatorio")
    @Min(value = 1, message = "El plazo debe ser al menos 1 mes")
    private Integer term;

    private String status; // Pending, Approved, Rejected

    private Long userId;
    private String userEmail;
    private LocalDateTime createdAt;
}