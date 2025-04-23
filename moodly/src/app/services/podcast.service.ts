import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PodcastService {
  private apiUrl = 'https://listen-api.listennotes.com/api/v2/search?q=mental%20health&type=podcast&size=10';

  constructor(private http: HttpClient) {}

  getPodcasts(query: string): Observable<any> {
    const url = `https://listen-api.listennotes.com/api/v2/search?q=${encodeURIComponent(query)}&type=podcast&size=10`;
    const headers = {
      'X-ListenAPI-Key': '49d5bf469cc44e5e8416bc225f177e77'  // replace with your actual key
    };
    return this.http.get(url, { headers });
  }
}
