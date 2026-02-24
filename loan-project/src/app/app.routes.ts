import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login'; // Revisa que esta ruta sea exacta
import { UserDashboard } from './components/user-dashboard/user-dashboard';
import { AdminDashboard } from './components/admin-dashboard/admin-dashboard';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { 
    path: 'user-dashboard', 
    component: UserDashboard, 
    canActivate: [authGuard] 
  },
  { 
    path: 'admin-dashboard', 
    component: AdminDashboard, 
    canActivate: [authGuard] 
  },
  { path: '**', redirectTo: 'login' }
];