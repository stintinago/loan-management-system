package com.makers.loans.application.ports.input.output;

import com.makers.loans.domain.model.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Optional<Loan> findById(Long id);
    List<Loan> findByUserId(Long userId);
    List<Loan> findAll();
}