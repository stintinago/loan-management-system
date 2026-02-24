package com.makers.loans.infraestructure.adapters.input.rest;

import com.makers.loans.application.usecases.LoanService;
import com.makers.loans.domain.model.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestLoan(@RequestBody Map<String, Object> payload) {
        System.out.println("Solicitud recibida: " + payload);
        try {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Préstamo solicitado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al procesar la solicitud");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getMyLoans(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getMyLoans(userId));
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Loan>> getAll() {
        return ResponseEntity.ok(loanService.getAllLoansForAdmin());
    }

    @PatchMapping("/admin/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Loan> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(loanService.updateLoanStatus(id, status));
    }
}