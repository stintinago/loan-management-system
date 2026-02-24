package com.makers.loans.application.usecases;

import com.makers.loans.application.ports.input.output.LoanRepositoryPort;
import com.makers.loans.domain.model.Loan;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepositoryPort loanRepositoryPort;

    public LoanService(LoanRepositoryPort loanRepositoryPort) {
        this.loanRepositoryPort = loanRepositoryPort;
    }

    public Loan requestLoan(Loan loan) {
        loan.setStatus("PENDING");
        return loanRepositoryPort.save(loan);
    }

    public List<Loan> getMyLoans(Long userId) {
        return loanRepositoryPort.findByUserId(userId);
    }

    public List<Loan> getAllLoansForAdmin() {
        return loanRepositoryPort.findAll();
    }

    public Loan updateLoanStatus(Long id, String status) {
        return loanRepositoryPort.findById(id).map(loan -> {
            loan.setStatus(status);
            return loanRepositoryPort.save(loan);
        }).orElseThrow(() -> new RuntimeException("Loan not found"));
    }
}