import { Component } from '@angular/core';
import { SidebarComponent } from './sidebar/sidebar.component';
import { RouterLink} from '@angular/router';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'moodly';
}
