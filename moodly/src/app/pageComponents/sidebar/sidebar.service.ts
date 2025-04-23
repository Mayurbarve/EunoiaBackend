import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {
  private collapsedSubject = new BehaviorSubject<boolean>(true);
  isCollapsed$ = this.collapsedSubject.asObservable();

  setSidebarState(state: boolean) {
    this.collapsedSubject.next(state);
  }

  toggleSidebarState() {
    this.collapsedSubject.next(!this.collapsedSubject.value);
  }

  getCurrentState() {
    return this.collapsedSubject.value;
  }
}
