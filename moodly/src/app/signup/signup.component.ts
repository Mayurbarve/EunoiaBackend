import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, RouterOutlet, RouterLink],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  signupForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router // ✅ Inject Router here
  ) {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contact: ['', Validators.required],
      age: ['', Validators.required],
      designation: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.signupForm.valid) {
      console.log('Form Data:', this.signupForm.value);

      this.http.post('http://localhost:8080/sign-up', this.signupForm.value).subscribe({
        next: res => {
          console.log('Signup Success:', res);

          localStorage.setItem('user', JSON.stringify(res));

          this.router.navigate(['/home']); // ✅ Redirect to home page
        },
        error: err => console.error('Signup Error:', err)
      });
    } else {
      console.log('Form Invalid');
    }
  }
}
