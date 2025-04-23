import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SidebarService } from './sidebar.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterOutlet],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  user!: User;
  isSidebarCollapsed = true;
  userId = 1; // Replace with actual user ID from auth later

  constructor(
    private sidebarService: SidebarService,
    private userService: UserService
  ) {
    // Subscribe to sidebar state
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.isSidebarCollapsed = state;
    });
  }

  ngOnInit(): void {
    this.fetchUserData();
  }

  toggleSidebar(): void {
    this.sidebarService.toggleSidebarState();
  }

  fetchUserData(): void {
    this.userService.getUserById(this.userId).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (error) => {
        console.error('Error fetching user:', error);
      }
    });
  }
}
