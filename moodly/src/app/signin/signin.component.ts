import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.loginForm = this.fb.group({
      email: [''],
      password: ['']
    });
  }

  onLogin() {
    const { email, password } = this.loginForm.value;

    this.http.get<any>(`http://localhost:8080/users/${email}`).subscribe({
      next: (user) => {
        if (user && user.password === password) {
          console.log("Login successful ✅", user);
          this.router.navigate(['/home']);
        } else {
          alert("❌ Invalid credentials");
        }
      },
      error: () => {
        alert("⚠️ User not found");
      }
    });
  }
}
