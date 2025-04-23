import { Component, OnInit, inject } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service';
import { JournalService, JournalEntry } from '../../services/journal.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-journal',
  standalone: true,
  imports: [CommonModule, NgClass, HttpClientModule, FormsModule],
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.css']
})
export class JournalComponent implements OnInit {
  sidebarCollapsed = true;
  showWriteModal = false;
  showReadModal = false;
  journalEntries: JournalEntry[] = [];
  newEntryContent: string = '';
  newEntryTitle: string = ''; // ✅ Added

  // Replace this with actual logged-in user ID if available
  userId: number = 1;

  private journalService = inject(JournalService);
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
    this.loadJournals();
  }

  loadJournals(): void {
    this.journalService.getAllJournals().subscribe({
      next: (entries) => {
        this.journalEntries = entries.reverse(); // Show latest first
      },
      error: (err) => {
        console.error('Failed to load journals:', err);
      }
    });
  }

  openWriteModal(): void {
    this.showWriteModal = true;
  }

  closeWriteModal(): void {
    this.showWriteModal = false;
    this.newEntryContent = '';
    this.newEntryTitle = '';
  }

  openReadModal(): void {
    this.showReadModal = true;
  }

  closeReadModal(): void {
    this.showReadModal = false;
  }

  saveJournal(): void {
    if (!this.newEntryTitle.trim() || !this.newEntryContent.trim()) return;

    const newEntry: JournalEntry = {
      date: new Date().toISOString().split('T')[0],
      title: this.newEntryTitle.trim(), // ✅ Include title
      content: this.newEntryContent.trim()
    };

    this.journalService.createJournal(this.userId, newEntry).subscribe({
      next: (saved) => {
        this.journalEntries.unshift(saved); // Add new entry at the top
        this.newEntryTitle = '';
        this.newEntryContent = '';
        this.closeWriteModal();
      },
      error: (err) => {
        console.error('Failed to save journal:', err);
      }
    });
  }
}
