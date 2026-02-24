import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs'; 

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth';
  

  private userSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('user') || 'null'));
  user$ = this.userSubject.asObservable();

  login(credentials: any): Observable<any> {
    // Simulating backend response
    let mockUser;
    if (credentials.email === 'admin@test.com') {
      mockUser = { email: 'admin@test.com', role: 'ROLE_ADMIN' };
    } else {
      mockUser = { email: 'user@test.com', role: 'ROLE_USER' };
    }
    
    // Save subject in localstorage
    this.userSubject.next(mockUser);
    localStorage.setItem('user', JSON.stringify(mockUser));
    
    return of(mockUser); 
  }

  logout() {
    this.userSubject.next(null);
    localStorage.removeItem('user');
  }

  get currentUser() {
    return this.userSubject.value;
  }
}