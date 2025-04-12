import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { NgClass } from '@angular/common'; 

@Component({
  selector: 'app-mood',
  standalone: true,
  imports: [ CommonModule, NgClass ], // Add NgClass for dynamic class binding
  // Fix typo: was "styleUrl"
  templateUrl: './mood.component.html',
  styleUrl: './mood.component.css'
})
export class MoodComponent {
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
