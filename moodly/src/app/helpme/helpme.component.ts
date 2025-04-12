import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { NgClass } from '@angular/common'; // Needed for [ngClass] in template

@Component({
  selector: 'app-helpme',
  standalone: true,
  imports: [CommonModule, NgClass ], // Add NgClass for dynamic class binding 
  templateUrl: './helpme.component.html',
  styleUrl: './helpme.component.css'
})
export class HelpmeComponent {
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
