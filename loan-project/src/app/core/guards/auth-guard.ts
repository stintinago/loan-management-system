import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth';

export const authGuard = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // If user in the service is allowed to continue
  if (authService.currentUser) {
    return true;
  }

  // If not, is redirected to the login
  router.navigate(['/login']);
  return false;
};