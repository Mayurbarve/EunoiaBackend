import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../sidebar/sidebar.service';
import { Activity, ActivityService } from '../../services/activity.service';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [CommonModule,],
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent {
  title = 'my-app';
  boolean_val = true;

  showmessage = "hello";
  changebutton() {
    this.boolean_val = !this.boolean_val;
  }

  // Mental health support test questions
  questions = [
    { id: 1, text: 'I feel down, depressed, or hopeless.', options: [
      { text: 'Not at all right now', value: 0 },
      { text: 'A little bit', value: 1 },
      { text: 'Quite a bit', value: 2 },
      { text: 'Extremely', value: 3 }
    ] },
    { id: 2, text: 'I have little interest or pleasure in doing things.', options: [
      { text: 'Not at all today', value: 0 },
      { text: 'Slightly uninterested', value: 1 },
      { text: 'Mostly uninterested', value: 2 },
      { text: 'Totally uninterested', value: 3 }
    ] },
    { id: 3, text: 'I feel nervous, anxious, or on edge.', options: [
      { text: 'Calm and relaxed', value: 0 },
      { text: 'Mildly uneasy', value: 1 },
      { text: 'Quite anxious', value: 2 },
      { text: 'On edge all day', value: 3 }
    ] },
    { id: 4, text: 'I have trouble relaxing.', options: [
      { text: 'Completely relaxed', value: 0 },
      { text: 'Somewhat tense', value: 1 },
      { text: 'Struggling to relax', value: 2 },
      { text: 'Unable to relax at all', value: 3 }
    ] },
    { id: 5, text: 'I feel tired or have little energy.', options: [
      { text: 'Energized', value: 0 },
      { text: 'A little tired', value: 1 },
      { text: 'Very drained', value: 2 },
      { text: 'Exhausted', value: 3 }
    ] },
    { id: 6, text: 'I have trouble concentrating on things.', options: [
      { text: 'Very focused', value: 0 },
      { text: 'Slightly distracted', value: 1 },
      { text: 'Frequently distracted', value: 2 },
      { text: 'Can’t focus at all', value: 3 }
    ] },
    { id: 7, text: 'I feel restless or have trouble sitting still.', options: [
      { text: 'Very calm', value: 0 },
      { text: 'Slightly fidgety', value: 1 },
      { text: 'Very restless', value: 2 },
      { text: 'Constantly on edge', value: 3 }
    ] },
    { id: 8, text: 'I have trouble falling or staying asleep.', options: [
      { text: 'Slept well', value: 0 },
      { text: 'Minor issues sleeping', value: 1 },
      { text: 'Had difficulty sleeping', value: 2 },
      { text: 'Barely slept', value: 3 }
    ] },
    { id: 9, text: 'I feel hopeless about the future.', options: [
      { text: 'Hopeful', value: 0 },
      { text: 'A little uncertain', value: 1 },
      { text: 'Quite hopeless', value: 2 },
      { text: 'Completely hopeless', value: 3 }
    ] },
    { id: 10, text: 'I have thoughts of self-harm or suicide.', options: [
      { text: 'No such thoughts', value: 0 },
      { text: 'Some negative thoughts', value: 1 },
      { text: 'Occasional harmful thoughts', value: 2 },
      { text: 'Persistent harmful thoughts', value: 3 }
    ] },
    { id: 11, text: 'I feel overwhelmed by stress.', options: [
      { text: 'Very calm today', value: 0 },
      { text: 'A little stressed', value: 1 },
      { text: 'Stressed most of the day', value: 2 },
      { text: 'Overwhelmed right now', value: 3 }
    ] },
    { id: 12, text: 'I have difficulty managing my emotions.', options: [
      { text: 'Emotionally balanced', value: 0 },
      { text: 'Slightly reactive', value: 1 },
      { text: 'Emotionally unstable', value: 2 },
      { text: 'Completely overwhelmed', value: 3 }
    ] },
    { id: 13, text: 'I feel isolated or lonely.', options: [
      { text: 'Connected and supported', value: 0 },
      { text: 'Mildly lonely', value: 1 },
      { text: 'Quite lonely', value: 2 },
      { text: 'Extremely isolated', value: 3 }
    ] },
    { id: 14, text: 'I have difficulty making decisions.', options: [
      { text: 'Very clear-headed', value: 0 },
      { text: 'Slightly indecisive', value: 1 },
      { text: 'Struggling to decide', value: 2 },
      { text: 'Totally stuck', value: 3 }
    ] },
    { id: 15, text: 'I feel anxious in social situations.', options: [
      { text: 'Socially comfortable', value: 0 },
      { text: 'Mildly uneasy', value: 1 },
      { text: 'Socially anxious', value: 2 },
      { text: 'Avoiding social contact', value: 3 }
    ] }
  ];

  // Store user answers as a map questionId -> selected option value
  userAnswers: { [key: number]: number } = {};

  // Store the test result score
  score: number | null = null;

  // Flag to show result or test form
  showResult = false;

  // Sidebar collapsed state
  sidebarCollapsed = true;

  // Handle option selection
  selectOption(questionId: number, value: number) {
    this.userAnswers[questionId] = value;
  }


  // Reset the test
  resetTest() {
    this.userAnswers = {};
    this.score = null;
    this.showResult = false;
  }

  constructor(
    private sidebarService: SidebarService,
    private activityService: ActivityService
  ) {
    this.sidebarService.isCollapsed$.subscribe(state => {
      this.sidebarCollapsed = state;
    });
  }

  get containerClass() {
    return this.sidebarCollapsed ? 'ml-[80px]' : 'ml-[230px]';
  }
  
   // Submit the test and calculate score
  submitTest() {
    console.log('submitTest called');
    this.score = Object.values(this.userAnswers).reduce((acc, val) => acc + val, 0);
    this.showResult = true;
  
    const userId = 1; // Replace this with actual user ID (maybe from login or context)
    const activity: Activity = {
      title: 'Mental Health Assessment',
      description: 'User completed mental health self-test',
      score: this.score!
    };
  
    this.activityService.createActivity(userId, activity).subscribe({
      next: (res) => console.log('Activity stored:', res),
      error: (err) => console.error('Failed to store activity', err)
    });
  }
}
