package com.makers.loans.infraestructure.adapters.output.persistence.repository;

import com.makers.loans.infraestructure.adapters.output.persistence.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByUserId(Long userId);
}
