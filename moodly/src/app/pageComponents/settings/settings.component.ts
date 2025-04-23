import { Component } from '@angular/core';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { CommonModule, NgClass } from '@angular/common'; // Needed for [ngClass] in template
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [NgClass, CommonModule, FormsModule],
  templateUrl: './settings.component.html',
})
export class SettingsComponent {
  sidebarCollapsed = true;

  constructor(private sidebarService: SidebarService) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }
  
  // Matches sidebar padding

  // Personalization Options
  selectedTheme: string = 'Default';
  selectedFontSize: string = 'Medium';

  // Notifications
  moodReminderTime: string = '08:00';
  activityTips: boolean = true;
  emailNotifications: boolean = false;

  // Accessibility
  textToSpeech: boolean = false;
  colorBlindMode: boolean = false;

  // Handlers
  onThemeChange(theme: string) {
    this.selectedTheme = theme;
    // Apply theme logic here (optional)
  }

  onFontSizeChange(size: string) {
    this.selectedFontSize = size;
    // Apply font size logic here (optional)
  }

  toggleTips() {
    this.activityTips = !this.activityTips;
  }

  toggleEmailNotifications() {
    this.emailNotifications = !this.emailNotifications;
  }

  toggleTTS() {
    this.textToSpeech = !this.textToSpeech;
  }

  toggleColorBlind() {
    this.colorBlindMode = !this.colorBlindMode;
  }

  enableAppLock() {
    alert('App Lock feature coming soon...');
  }

  downloadData() {
    alert('Your data download will be prepared.');
  }

  deleteAccount() {
    const confirmDelete = confirm('Are you sure you want to delete your account? This action is irreversible.');
    if (confirmDelete) {
      alert('Your account will be deleted.');
    }
  }

  createGoal() {
    alert('Goal creation feature coming soon...');
  }

  manageContacts() {
    alert('Managing trusted contacts will be available soon...');
  }
}
