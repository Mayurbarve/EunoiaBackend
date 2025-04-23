import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MoodEntry {
  id?: number;
  mood: string;
  activity: string;
  reflection: string;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class MoodService {
  private baseUrl = 'http://localhost:8080/users/moods';

  constructor(private http: HttpClient) {}

  // Create a new mood entry
  createMood(userId: number, mood: MoodEntry): Observable<MoodEntry> {
    return this.http.post<MoodEntry>(`${this.baseUrl}/${userId}`, mood);
  }

  // Fetch all mood entries
  getAllMoods(): Observable<MoodEntry[]> {
    return this.http.get<MoodEntry[]>(this.baseUrl);
  }

  // Get mood by ID (optional for future use)
  getMoodById(id: number): Observable<MoodEntry> {
    return this.http.get<MoodEntry>(`${this.baseUrl}/${id}`);
  }

  // Update an existing mood (optional for future use)
  updateMood(id: number, userId: number, mood: MoodEntry): Observable<MoodEntry> {
    return this.http.put<MoodEntry>(`${this.baseUrl}/${id}`, mood);
  }

  // Delete a mood entry (optional for future use)
  deleteMood(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
