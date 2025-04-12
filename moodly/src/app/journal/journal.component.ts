import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { NgClass } from '@angular/common'; // Needed for [ngClass] in template

@Component({
  selector: 'app-journal',
  standalone: true,
  imports: [CommonModule, NgClass],
  templateUrl: './journal.component.html',
  styleUrl: './journal.component.css'
})
export class JournalComponent {
  sidebarCollapsed = true;

  constructor(private sidebarService: SidebarService) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }

  showWriteModal: boolean = false;
  showReadModal: boolean = false;

  openWriteModal(): void {
    this.showWriteModal = true;
  }

  closeWriteModal(): void {
    this.showWriteModal = false;
  }

  openReadModal(): void {
    this.showReadModal = true;
  }

  closeReadModal(): void {
    this.showReadModal = false;
  }
}
