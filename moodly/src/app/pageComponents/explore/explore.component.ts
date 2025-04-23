import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { BoredApiService } from '../../services/bored-api.service';
import { PexelsApiService } from '../../services/pexels-api.service';
import { FirstAidService } from '../../services/first-aid.service';
import { PodcastService } from '../../services/podcast.service';
import { BookService } from '../../services/book.service';


interface Infographic {
  title: string;
  image: string;
}


export interface Book {
  title: string;
  author: string;
  cover: string;
  rating: number;
}

interface Podcast {
  title: string;
  host: string;
  image: string;
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
  imports: [CommonModule, RouterModule],
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css', './explore.interactive.css']
})
export class ExploreComponent implements OnInit {
  mindfulChallenge: string = 'Loading...';
  dailyChallenge: string = '';
  challengeCompleted: boolean = false;
  currentDay: number = 1;

  constructor(
    private boredService: BoredApiService,
    private pexelsService: PexelsApiService,
    private firstAidService: FirstAidService,
    private bookService: BookService,
    private podcastService: PodcastService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}
  ngOnInit(): void {
    this.currentDay = this.getDayIndex();
    this.loadChallenge();
    this.loadBooks();
    this.fetchPodcasts('mental health');
    this.loadInfographics(); // 👈 Added call to load images
  }


//30 days Challenges
  getDayIndex(): number {
    const start = new Date('2024-04-01'); // Start of the challenge
    const today = new Date();
    const diff = Math.floor((+today - +start) / (1000 * 60 * 60 * 24)) + 1;
    return Math.min(diff, 30); // Clamp to 30 days max
  }
  
  loadChallenge(): void {
    if (isPlatformBrowser(this.platformId)) {
      const cached = localStorage.getItem('dailyChallenge_' + this.currentDay);
      if (cached) {
        const data = JSON.parse(cached);
        this.dailyChallenge = data.challenge;
        this.challengeCompleted = data.completed;
        return;
      }
    }
  
    this.boredService.getMindfulActivity().subscribe({
      next: (data) => {
        this.dailyChallenge = data.activity;
  
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem('dailyChallenge_' + this.currentDay, JSON.stringify({
            challenge: data.activity,
            completed: false
          }));
        }
      },
      error: () => {
        this.dailyChallenge = 'Take 5 minutes to focus on your breath 🌬️';
      }
    });
  }
  
  markAsCompleted(): void {
    this.challengeCompleted = true;
  
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('dailyChallenge_' + this.currentDay, JSON.stringify({
        challenge: this.dailyChallenge,
        completed: true
      }));
    }
  }
  

  //infographics 
  
  loadInfographics(): void {
    this.pexelsService.getImages('mental health infographic', 6).subscribe({
      next: (res) => {
        this.infographics = res.photos.map((photo: any) => ({
          image: photo.src.medium,
          title: photo.photographer  // Optionally, you can use the photographer's name
        }));
      },
      error: (err) => {
        console.error('Error fetching infographics:', err);
      }
    });
  }
  
  

  infographics: Infographic[] = [];
//firstaid guide
  mentalFirstAidGuides = [
    { name: 'Depression', link: 'assets/mental-first-aid/depression.pdf' },
    { name: 'Anxiety', link: 'assets/mental-first-aid/anxiety.pdf' },
    { name: 'Stress Management', link: 'assets/mental-first-aid/stress-management.pdf' },
    { name: 'PTSD', link: 'assets/mental-first-aid/ptsd.pdf' },
    { name: 'Bipolar Disorder', link: 'assets/mental-first-aid/bipolar-disorder.pdf' },
  
  ];


//books
  books: Book[] = [];

  loadBooks(): void {
    this.bookService.getBooks().subscribe((res: any) => {
      this.books = res.items.map((item: any) => ({
        title: item.volumeInfo.title,
        author: item.volumeInfo.authors?.[0] || 'Unknown',
        cover: item.volumeInfo.imageLinks?.thumbnail || 'assets/placeholder.png',
        rating: item.volumeInfo.averageRating || 4, // Set default rating if not available
      }));
    });
  }
//podcasts
fetchPodcasts(query: string): void {
  this.podcastService.getPodcasts(query).subscribe(
    (response) => {
      // Transforming each podcast object to match the expected structure
      this.podcasts = response.results.map((p: any) => ({
        title: p.title_original,
        host: p.publisher_original,
        image: p.image
      }));
    },
    (error) => {
      console.error('Error fetching podcasts:', error);
    }
  );
}

  podcasts: Podcast[]=[];
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
