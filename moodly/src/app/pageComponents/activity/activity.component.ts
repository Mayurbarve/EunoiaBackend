import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { NgClass } from '@angular/common'; // Needed for [ngClass] in template
import { RouterLink, RouterOutlet } from '@angular/router';
import { QuoteService } from '../../services/quote.service';

@Component({
  selector: 'app-activity',
  standalone: true,
  imports: [CommonModule, NgClass, RouterOutlet, RouterLink ], // Add NgClass for dynamic class binding       
  templateUrl: './activity.component.html',
  styleUrl: './activity.component.css'
})
export class ActivityComponent implements OnInit {
   sidebarCollapsed = true;
   quote: string = '';
  author: string = '';
  
    constructor(private sidebarService: SidebarService, private quoteService: QuoteService) {
      this.sidebarService.isCollapsed$.subscribe(state => {
        this.sidebarCollapsed = state;
      });
    }
  
    get containerClass() {
      return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
    }

    ngOnInit(): void {
      console.log('ActivityComponent initialized'); // 👈 Do you see this?
      this.loadQuoteOfTheDay();
    }

    loadQuoteOfTheDay(): void {
      this.quoteService.getDailyQuote().subscribe({
        next: (data: any) => {
          this.quote = data[0]?.q || 'Stay positive!';
          this.author = data[0]?.a || 'Unknown';
        },
        error: (err) => {
          console.error('Failed to load quote:', err);
          this.quote = 'Take a deep breath and keep going.';
          this.author = 'Mindfulness Bot';
        }
      });
    }
  
}
