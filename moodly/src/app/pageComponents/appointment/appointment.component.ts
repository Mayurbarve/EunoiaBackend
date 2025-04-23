import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class AppointmentComponent {
  patientName: string = '';
  email: string = '';
  phone: string = '';
  preferredDate: string = '';
  preferredTime: string = '';
  selectedDoctor: string = '';

  doctors: string[] = [
    'Dr. Smith - Psychiatrist',
    'Dr. Johnson - Psychologist',
    'Dr. Lee - Therapist',
    'Dr. Patel - Counselor'
  ];

  submitted: boolean = false;

  submitAppointment() {
    if (this.patientName && this.email && this.phone && this.preferredDate && this.preferredTime && this.selectedDoctor) {
      this.submitted = true;
      // Here you can add logic to send the appointment data to a backend or service
      console.log('Appointment submitted:', {
        patientName: this.patientName,
        email: this.email,
        phone: this.phone,
        preferredDate: this.preferredDate,
        preferredTime: this.preferredTime,
        selectedDoctor: this.selectedDoctor
      });
    } else {
      alert('Please fill in all fields before submitting.');
    }
  }

  resetForm() {
    this.patientName = '';
    this.email = '';
    this.phone = '';
    this.preferredDate = '';
    this.preferredTime = '';
    this.selectedDoctor = '';
    this.submitted = false;
  }
}
