import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('slideInOut', [
      state('true', style({ transform: 'translateX(0)' })),
      state('false', style({ transform: 'translateX(-100%)' })),
      transition('true <=> false', animate('300ms ease-in-out')),
    ]),
  ]
})

export class AppComponent {
  title = 'image-selector';

  @ViewChild('sidenavRef', { static: true }) sidenavRef!: MatSidenav;
  isSidenavOpen = false;

  onNavSidebarToggle() {
    this.sidenavRef.toggle();
  }

  onBackdropClick() {
    this.sidenavRef.close();
    this.isSidenavOpen = !this.isSidenavOpen;
  }
}
