import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface JournalEntry {
  id?: number;
  date: string;
  title: string; // ✅ NEW FIELD
  content: string;
}

@Injectable({
  providedIn: 'root'
})
export class JournalService {

  private baseUrl = 'http://localhost:8080/users/journals';

  constructor(private http: HttpClient) {}

  // Get all journal entries
  getAllJournals(): Observable<JournalEntry[]> {
    return this.http.get<JournalEntry[]>(this.baseUrl);
  }

  // Get a journal by ID
  getJournalById(id: number): Observable<JournalEntry> {
    return this.http.get<JournalEntry>(`${this.baseUrl}/${id}`);
  }

  // Create a journal entry
  createJournal(userId: number, journal: JournalEntry): Observable<JournalEntry> {
    return this.http.post<JournalEntry>(`${this.baseUrl}/${userId}`, journal);
  }

  // Update a journal entry
  updateJournal(id: number, userId: number, journal: JournalEntry): Observable<JournalEntry> {
    return this.http.put<JournalEntry>(`${this.baseUrl}/${id}/${userId}`, journal);
  }

  // Delete a journal entry
  deleteJournal(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
