import { Component, OnInit, inject } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service';
import { MoodService, MoodEntry } from '../../services/mood.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-mood',
  standalone: true,
  imports: [CommonModule, NgClass, HttpClientModule, FormsModule],
  templateUrl: './mood.component.html',
  styleUrls: ['./mood.component.css']
})
export class MoodComponent implements OnInit {
  sidebarCollapsed = true;
  moodEntries: MoodEntry[] = [];
  selectedMood: string = '';
  activity: string = '';
  reflection: string = '';
  showWriteModal = false;
  showReadModal = false;

  // Replace with actual logged-in user ID if available
  userId: number = 1;

  private moodService = inject(MoodService);
  private sidebarService = inject(SidebarService);

  constructor() {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }

  ngOnInit(): void {
    this.loadMoods();
  }

  loadMoods(): void {
    this.moodService.getAllMoods().subscribe({
      next: (entries) => {
        this.moodEntries = entries.reverse(); // Show latest first
      },
      error: (err) => {
        console.error('Failed to load moods:', err);
      }
    });
  }

  selectMood(mood: string): void {
    this.selectedMood = mood;
  }

  openWriteModal(): void {
    this.showWriteModal = true;
  }

  closeWriteModal(): void {
    this.showWriteModal = false;
    this.selectedMood = '';
    this.activity = '';
    this.reflection = '';
  }

  openReadModal(): void {
    this.showReadModal = true;
  }

  closeReadModal(): void {
    this.showReadModal = false;
  }

  saveMood(): void {
    if (!this.selectedMood.trim() || !this.activity.trim() || !this.reflection.trim()) return;

    const newEntry: MoodEntry = {
      mood: this.selectedMood.trim(),
      activity: this.activity.trim(),
      reflection: this.reflection.trim(),
      date: new Date().toISOString()
    };

    this.moodService.createMood(this.userId, newEntry).subscribe({
      next: (saved) => {
        this.moodEntries.unshift(saved); // Add new entry at the top
        this.closeWriteModal();
      },
      error: (err) => {
        console.error('Failed to save mood:', err);
      }
    });
  }
}
