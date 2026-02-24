import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { Router } from '@angular/router';
import { LoanService } from '../../core/services/loan';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-dashboard.html',
  styleUrl: './user-dashboard.css'
})
export class UserDashboard implements OnInit {
  private loanService = inject(LoanService);
  private authService = inject(AuthService);
  private router = inject(Router);

  user: any = null;
  loans: any[] = [];
  amount: number = 0;
  term: number = 12;

  ngOnInit() {

    this.user = this.authService.currentUser;


    if (!this.user) {
      const savedUser = localStorage.getItem('user');
      if (savedUser) {
        this.user = JSON.parse(savedUser);
      } else {
        this.router.navigate(['/login']);
        return;
      }
    }
    this.loadLoans();
  }

  loadLoans() {

    const email = this.user?.email || 'usuario@test.com';
    this.loanService.getUserLoans(email).subscribe({
      next: (data) => this.loans = data,
      error: () => console.log('Error cargando préstamos, usando mocks')
    });
  }

  sendRequest() {
    if (this.amount <= 0) {
      alert('Por favor ingresa un monto válido');
      return;
    }
    
    const newLoan = {
      amount: this.amount,
      term: this.term,
      userEmail: this.user?.email
    };

    this.loanService.requestLoan(newLoan).subscribe({
      next: () => {
        alert('Solicitud enviada con éxito');
        this.loadLoans(); 
        this.amount = 0;
      },
      error: () => alert('Error al enviar solicitud')
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}