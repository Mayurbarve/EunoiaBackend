import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone: true,
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet, RouterLink]
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  isSubmitting = false;
  errorMessage: string | null = null;

  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contact: ['', [Validators.required, Validators.pattern('^[0-9]{10,}$')]],
      age: ['', [Validators.required, Validators.min(10), Validators.max(120)]],
      designation: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  signup(): void {
    console.log('Signup clicked');
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      console.warn('Form is invalid:', this.signupForm.value);
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = null;

    this.authService.signup(this.signupForm.value).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Signup failed:', error);
        this.errorMessage = 'Signup failed. Please try again.';
        this.isSubmitting = false;
      }
    });
  }
}
