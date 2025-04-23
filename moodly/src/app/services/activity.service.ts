// src/app/services/activity.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Activity {
  title: string;
  description: string;
  score: number;
}

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private baseUrl = 'http://localhost:8080/users/activities';

  constructor(private http: HttpClient) {}

  createActivity(userId: number, activity: Activity): Observable<any> {
    return this.http.post(`${this.baseUrl}/${userId}`, activity);
  }
}
