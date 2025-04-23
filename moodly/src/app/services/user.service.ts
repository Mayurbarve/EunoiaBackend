import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/User';

export interface JournalEntry {
  id: number;
  date: string;
  content: string;
  // Add other fields if present in your Journal model
}




@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/users';
  private journalUrl = 'http://localhost:8080/users/journals';

  constructor(private http: HttpClient) {}

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userId}`);
  }

  updateUser(userId: number, updatedUser: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${userId}`, updatedUser);
  }

  getAllJournals(): Observable<JournalEntry[]> {
    return this.http.get<JournalEntry[]>(this.journalUrl);
  }
}

