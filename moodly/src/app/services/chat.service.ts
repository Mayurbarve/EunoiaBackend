import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private apiUrl: string = 'https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent';
  private apiKey: string = 'AIzaSyD3kDrfLE8bCC1b-s-ZYlswixlZndS9-3A';  // Replace with your actual API key

  constructor(private http: HttpClient) {}

  sendToBot(message: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = {
      contents: [
        {
          parts: [
            {
              text: message
            }
          ]
        }
      ]
    };

    // Construct the URL with the API Key
    const url = `${this.apiUrl}?key=${this.apiKey}`;

    return this.http.post<any>(url, body, { headers });
  }
}
