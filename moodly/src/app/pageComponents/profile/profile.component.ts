import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService, JournalEntry } from '../../services/user.service';
import { CommonModule, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, NgClass, FormsModule], // Add necessary imports if needed
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {
  user !: User;
  editUser!: User;
  isEditModalOpen = false;
  sidebarCollapsed = true;
  userId = 1; // This should be dynamic based on logged-in user

  journalEntries: JournalEntry[] = [];


  constructor(private userService: UserService, private sidebarService: SidebarService) {

    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }



  ngOnInit() {
    this.fetchUserData();
    this.fetchJournalEntries();
  }

  fetchUserData() {
    this.userService.getUserById(this.userId).subscribe(
      (data) => {
        this.user = data;
        this.editUser = { ...data };
      },
      (error) => {
        console.error('Error fetching user:', error);
      }
    );
  }

  openEditModal() {
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
  }

  saveChanges() {
    this.userService.updateUser(this.userId, this.editUser).subscribe(
      (updated) => {
        this.user = updated;
        this.closeEditModal();
      },
      (error) => {
        console.error('Error updating user:', error);
      }
    );
  }

  handleImageUpload(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.editUser.profileImage = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  fetchJournalEntries(): void {
    this.userService.getAllJournals().subscribe({
      next: (entries) => {
        this.journalEntries = entries.reverse(); // Optional: newest first
      },
      error: (error) => {
        console.error('Error fetching journal entries:', error);
      }
    });
  }


}
