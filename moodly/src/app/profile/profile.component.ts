import { CommonModule, NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SidebarService } from '../sidebar/sidebar.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, NgClass],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user = {
    userId: 1,
    name: 'Mayur Dudhbarve',
    contact: 9876543210,
    email: 'mayur@example.com',
    age: 23,
    designation: 'Student',
    date: '2023-01-01',
    profileImage: 'assets/jouno-bg.jpg' // placeholder
  };

  editUser = { ...this.user };
  isEditModalOpen = false;
  selectedImage: File | null = null;

  openEditModal() {
    this.editUser = { ...this.user };
    this.selectedImage = null;
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
  }

  saveChanges() {
    this.user = { ...this.editUser };
    if (this.selectedImage) {
      const reader = new FileReader();
      reader.onload = () => {
        this.user.profileImage = reader.result as string;
      };
      reader.readAsDataURL(this.selectedImage);
    }
    this.isEditModalOpen = false;
  }

  handleImageUpload(event: any) {
    this.selectedImage = event.target.files[0];
  }

  //Sidebr 
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
