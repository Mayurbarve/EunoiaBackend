import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterOutlet, RouterLink],
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  credentials = {
    email: '',
    password: ''
  };

  errorMessage = '';
  isLoading = false;

  constructor(private http: HttpClient, private router: Router) {}

  onLogin() {
    if (!this.credentials.email || !this.credentials.password) {
      this.errorMessage = 'Please enter both email and password.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.http.post<{ token: string }>('http://localhost:8080/authenticate', this.credentials)
      .subscribe({
        next: response => {
          localStorage.setItem('token', response.token);
          this.router.navigate(['/home']);
        },
        error: err => {
          console.error('Login failed:', err);
          this.errorMessage = 'Invalid email or password. Please try again.';
        },
        complete: () => {
          this.isLoading = false;
        }
      });
  }
}
