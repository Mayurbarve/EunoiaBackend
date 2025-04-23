import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PexelsApiService {
  // 🔑 Paste your actual API key here
  private API_KEY = 'jqQoT4g855gLcLQqt2KqEvC0CvFsUSNZq1ZqztAPqtE4wQYFId0ZTIPD';
  private BASE_URL = 'https://api.pexels.com/v1/search';

  constructor(private http: HttpClient) {}

  getImages(query: string = 'mental health infographic', perPage: number = 6): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: this.API_KEY  // Make sure you paste your real API key here
    });
  
    return this.http.get(this.BASE_URL, {
      headers,
      params: {
        query,             // Now this will default to "mental health infographic"
        per_page: perPage.toString()  // You can adjust this to get more or fewer images
      }
    });
  }
  
}
