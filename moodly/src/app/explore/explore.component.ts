import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SidebarService } from '../sidebar/sidebar.service'; // Adjust path if needed
import { NgClass } from '@angular/common'; // Needed for [ngClass] in template



interface Challenge {
  day: number;
  title: string;
  description: string;
  completed: boolean;
}

interface Infographic {
  title: string;
  image: string;
}

interface FirstAidGuide {
  title: string;
}

interface Book {
  title: string;
  author: string;
  cover: string;
  rating: number;
}

interface Podcast {
  title: string;
  host: string;
}

interface SuccessStory {
  initials: string;
  name: string;
  timeAgo: string;
  testimonial: string;
  topic: string;
  likes: number;
}

@Component({
  selector: 'app-explore',
  standalone: true,
  imports: [CommonModule, RouterModule, NgClass],
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css', './explore.interactive.css']
})
export class ExploreComponent {
  sidebarCollapsed = true;

  constructor(private sidebarService: SidebarService) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }
  
  challenges: Challenge[] = [
    { 
      day: 1, 
      title: '5-Minute Breathing', 
      description: 'Practice deep breathing for 5 minutes', 
      completed: true 
    },
    { 
      day: 2, 
      title: 'Gratitude Journaling', 
      description: "Write down 3 things you're grateful for", 
      completed: true 
    },
    { 
      day: 3, 
      title: 'Body Scan Meditation', 
      description: 'Perform a full body scan meditation', 
      completed: false 
    }
  ];

  infographics: Infographic[] = [
    { 
      title: 'Anxiety Symptoms', 
      image: 'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=500&auto=format' 
    },
    { 
      title: 'Sleep Hygiene', 
      image: 'https://images.unsplash.com/photo-1516589178581-6cd7833ae3b2?w=500&auto=format' 
    },
    { 
      title: 'Stress Management', 
      image: 'https://images.unsplash.com/photo-1494412651409-8963ce7935a7?w=500&auto=format' 
    }
  ];

  firstAidGuides: FirstAidGuide[] = [
    { title: 'Panic Attack First Aid' },
    { title: 'Depression Support Guide' }
  ];

  books: Book[] = [
    { 
      title: 'The Mindful Way Through Depression', 
      author: 'Mark Williams', 
      cover: 'https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=500&auto=format', 
      rating: 4.5 
    },
    { 
      title: 'The Anxiety Toolkit', 
      author: 'Alice Boyes', 
      cover: 'https://images.unsplash.com/photo-1541963463532-d68292c34b19?w=500&auto=format', 
      rating: 4.2 
    },
    { 
      title: 'Atomic Habits', 
      author: 'James Clear', 
      cover: 'https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=500&auto=format', 
      rating: 4.7 
    }
  ];

  podcasts: Podcast[] = [
    { title: 'The Happiness Lab', host: 'Dr. Laurie Santos' }
  ];

  successStories: SuccessStory[] = [
    { 
      initials: 'JS', 
      name: 'Jane Smith', 
      timeAgo: '2 weeks ago', 
      testimonial: 'The 30-day challenge completely changed my morning routine and mental clarity', 
      topic: 'mindfulness',
      likes: 24 
    },
    { 
      initials: 'MD', 
      name: 'Michael Doe', 
      timeAgo: '1 month ago', 
      testimonial: 'The book recommendations helped me understand and manage my anxiety better', 
      topic: 'anxiety',
      likes: 18 
    }
  ];
}
