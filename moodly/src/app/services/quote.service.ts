import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {
  private apiUrl = 'https://zenquotes.io/api/today'; // Replace with your chosen API endpoint

  constructor(private http: HttpClient) {}

  getDailyQuote(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}