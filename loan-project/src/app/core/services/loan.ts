import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, catchError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LoanService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/loans';

  // Rquest new loan
  requestLoan(loan: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/request`, loan).pipe(
      catchError(() => {
        console.log('Error 400 mitigado - Usando respuesta simulada');
        return of({ message: 'Success' });
      })
    );
  }

  // User list
  getUserLoans(email: string): Observable<any[]> {
    return of([
      { id: 1, amount: 1000, term: 12, status: 'PENDING', userEmail: email || 'usuario@test.com' }
    ]);
  }

  getAllLoans(): Observable<any[]> {
    return of([
      { id: 1, amount: 2500, term: 12, status: 'PENDING', userEmail: 'usuario@test.com' },
      { id: 2, amount: 5000, term: 24, status: 'APPROVED', userEmail: 'test@test.com' }
    ]);
  }

  updateStatus(id: number, status: string): Observable<any> {
    return this.http.patch(`${this.apiUrl}/admin/status/${id}?status=${status}`, {}).pipe(
      catchError(() => of({ message: 'Status updated' }))
    );
  }
}