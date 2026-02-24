package com.makers.loans.infraestructure.adapters.output.persistence;

import com.makers.loans.application.ports.input.output.LoanRepositoryPort;
import com.makers.loans.domain.model.Loan;
import com.makers.loans.infraestructure.adapters.output.persistence.entity.LoanEntity;
import com.makers.loans.infraestructure.adapters.output.persistence.repository.JpaLoanRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoanPersistenceAdapter implements LoanRepositoryPort {

    private final JpaLoanRepository jpaRepository;

    public LoanPersistenceAdapter(JpaLoanRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = new LoanEntity(
                loan.getId(), loan.getAmount(), loan.getTerm(),
                loan.getStatus(), loan.getUserId(), loan.getUserEmail(), loan.getCreatedAt()
        );
        LoanEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public List<Loan> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return jpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Loan> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::mapToDomain).collect(Collectors.toList());
    }

    private Loan mapToDomain(LoanEntity e) {
        return new Loan(e.getId(), e.getAmount(), e.getTerm(), e.getStatus(), e.getUserId(), e.getUserEmail(), e.getCreatedAt());
    }
}