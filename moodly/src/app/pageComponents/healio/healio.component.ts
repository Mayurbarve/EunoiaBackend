import { Component } from '@angular/core';
import { SidebarService } from '../sidebar/sidebar.service';
import { CommonModule, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatService } from '../../services/chat.service';

@Component({
  selector: 'app-healio',
  standalone: true,
  imports: [NgClass, CommonModule, FormsModule],
  templateUrl: './healio.component.html',
  styleUrls: ['./healio.component.css']
})
export class HealioComponent {
  sidebarCollapsed = true;

  constructor(
    private sidebarService: SidebarService,
    private chatService: ChatService // Inject the service
  ) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }

  messages = [
    { sender: 'bot', text: 'Hello! I’m here to help. How are you feeling today?' }
  ];
  userInput: string = '';

  sendMessage() {
    if (this.userInput.trim() !== '') {
      const userMessage = this.userInput;
      this.messages.push({ sender: 'user', text: userMessage });
      this.userInput = '';

      // Call the backend with user input via ChatService
      this.chatService.sendToBot(userMessage).subscribe({
        next: (response) => {
          console.log('Gemini response:', response); // 👈 See what you're getting!
          const botMessage = response?.candidates?.[0]?.content?.parts?.[0]?.text || 'Sorry, I could not understand.';
          this.messages.push({ sender: 'bot', text: botMessage });
        },
        
        error: () => {
          this.messages.push({ sender: 'bot', text: 'Oops! Something went wrong. Try again later.' });
        }
      });
    }
  }
}
