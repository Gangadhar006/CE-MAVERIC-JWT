
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenKey = 'auth_token';
  
  constructor() {}

  // Check if the user is logged in
  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  // Get the user's role from the token
  getUserRole(): string | null {
    const token = this.getToken();
    if (token) {
      // Parse the token and extract the role information
      // Return the role (customer, admin, etc.)
    }
    return null;
  }

  // Store the token
  setToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  // Retrieve the token
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Remove the token (logout)
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}
