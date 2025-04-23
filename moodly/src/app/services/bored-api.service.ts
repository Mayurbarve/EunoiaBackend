import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BoredApiService {
  private apiUrl = 'https://www.boredapi.com/api/activity?type=relaxation';

  constructor(private http: HttpClient) {}

  getMindfulActivity(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
