import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SidebarService } from './sidebar.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterOutlet],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  isSidebarCollapsed = true;

  constructor(private sidebarService: SidebarService) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.isSidebarCollapsed = state;
    });
  }

  toggleSidebar() {
    this.sidebarService.toggleSidebarState();
  }
}
