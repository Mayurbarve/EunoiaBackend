import { Routes } from "@angular/router";
import { AuthGuard } from "./auth.guard";
import { WelcomeComponent } from "./welcome/welcome.component";
import { LoginComponent } from "./components/login/login.component";
import { SignupComponent } from "./components/signup/signup.component";

import { LayoutComponent } from "./layout/layout.component";
import { ProfileComponent } from "./pageComponents/profile/profile.component";
import { HomeComponent } from "./pageComponents/home/home.component";
import { ActivityComponent } from "./pageComponents/activity/activity.component";
import { JournalComponent } from "./pageComponents/journal/journal.component";
import { MoodComponent } from "./pageComponents/mood/mood.component";
import { SchedulesComponent } from "./pageComponents/schedules/schedules.component";
import { SettingsComponent } from "./pageComponents/settings/settings.component";
import { HelpmeComponent } from "./pageComponents/helpme/helpme.component";
import { ExploreComponent } from "./pageComponents/explore/explore.component";
import { HealioComponent } from "./pageComponents/healio/healio.component";
import { TestComponent } from "./pageComponents/test/test.component";
import { DashboardComponent } from "./pageComponents/dashboard/dashboard.component";



export const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: 'home', component: HomeComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'activity', component: ActivityComponent },
      { path: 'journals', component: JournalComponent },
      { path: 'mood', component: MoodComponent },
      { path: 'schedules', component: SchedulesComponent },
      { path: 'setting', component: SettingsComponent },
      { path: 'help', component: HelpmeComponent },
      { path: 'explore', component: ExploreComponent },
      { path: 'healio', component: HealioComponent },
      { path: 'test', component: TestComponent }
    ]
  },
];
