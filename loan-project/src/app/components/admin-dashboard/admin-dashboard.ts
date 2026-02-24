import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanService } from '../../core/services/loan';
import { AuthService } from '../../core/services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css'
})
export class AdminDashboard implements OnInit {
  private loanService = inject(LoanService);
  private authService = inject(AuthService);
  private router = inject(Router);
  
  loans: any[] = [];

  ngOnInit() {
    this.loadAllLoans();
  }

  loadAllLoans() {
    this.loanService.getAllLoans().subscribe(data => this.loans = data);
  }

  updateStatus(id: number, status: string) {
    this.loanService.updateStatus(id, status).subscribe(() => {
      const loan = this.loans.find(l => l.id === id);
      if (loan) loan.status = status;
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}