import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-login',
  standalone: true, // ✅ if you're using standalone component
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet, RouterLink] // ✅ ADD THIS
})
export class LoginComponent implements OnInit {


  loginForm !: FormGroup;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    })
  }

  login() {
    if (this.loginForm.invalid) return;
  
    this.service.login(this.loginForm.value).subscribe({
      next: (response) => {
        if (response.jwtToken) {
          localStorage.setItem('JWT', response.jwtToken);
          this.router.navigate(['home']);
        }
      },
      error: (err) => {
        console.error("Login failed", err);
        // Optionally show toast / error message
      }
    });
  }
  

}
