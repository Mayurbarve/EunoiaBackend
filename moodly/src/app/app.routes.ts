
import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { ActivityComponent } from './activity/activity.component';
import { MoodComponent } from './mood/mood.component';
import { SchedulesComponent } from './schedules/schedules.component';
import { SettingsComponent } from './settings/settings.component';
import { JournalComponent } from './journal/journal.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HelpmeComponent } from './helpme/helpme.component';
import { ProfileComponent } from './profile/profile.component';
import { ExploreComponent } from './explore/explore.component';

export const routes: Routes = [
  {path: 'signup',component:SignupComponent},
  {path: 'signin',component:SigninComponent},

  {path:  'profile',component:ProfileComponent},
  { path: 'home', component: HomeComponent },

  { path: 'dashboard', component: DashboardComponent },
  { path: 'activity', component: ActivityComponent},
  { path: 'journals', component: JournalComponent},
  { path: 'mood', component: MoodComponent},
  { path: 'schedules', component: SchedulesComponent},
  { path: 'setting', component: SettingsComponent},
  {path:  'help',component:HelpmeComponent},
  {path: 'explore', component:ExploreComponent},


  { path: '', redirectTo: 'home', pathMatch: 'full' },  // Default route
  { path: '**', redirectTo: 'home' } // Wildcard route for unknown paths
];

