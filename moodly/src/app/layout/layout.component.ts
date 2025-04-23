import { Component } from '@angular/core';
import { SidebarComponent } from '../pageComponents/sidebar/sidebar.component';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [ SidebarComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

}
