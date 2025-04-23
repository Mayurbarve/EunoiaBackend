import { Component } from '@angular/core';
import emailjs from '@emailjs/browser';
import { FormsModule, NgModel } from '@angular/forms'; // Import FormsModule
import { CommonModule } from '@angular/common'; // Import CommonModule if needed for template functionality
import { SidebarService } from '../sidebar/sidebar.service';
 
@Component({
  selector: 'app-helpme',
  standalone: true, // Declare this as a standalone component
  imports: [FormsModule, CommonModule],
  templateUrl: './helpme.component.html',
  styleUrls: ['./helpme.component.css']
})
export class HelpmeComponent {
  formData = {
    name: '',
    email: '',
    query: ''
  };

  submitForm(contactForm: any): void {
    const serviceID = 'service_zkzar0r';
    const templateID = 'template_it18zh8';
    const publicKey = 'PhPx-PrO0A2xtqEb5';

    emailjs.send(serviceID, templateID, this.formData, publicKey)
      .then((response: { status: number; text: string }) => {
        console.log('SUCCESS!', response.status, response.text);
        alert('Your query has been sent successfully!');
        if (contactForm) {
          contactForm.resetForm();
        }
      })
      .catch((error: { message: string }) => {
        console.error('FAILED...', error.message);
        alert('Failed to send your query. Please try again later.');
      });
  }

  sidebarCollapsed = true;

  constructor(private sidebarService: SidebarService) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }
}
