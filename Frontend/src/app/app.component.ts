import { Component } from '@angular/core';
import { RouterOutlet, Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'campus-Afrique-Frontend';
  showNavbar = true;
  showFooter = true;

  constructor(private router: Router) {
    // Écouter les événements de navigation
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Vérifier si on est sur la page profil
        const hideNavFooter = event.url === '/profil' || event.url.includes('/profil');

        // Masquer navbar et footer si on est sur cette page
        this.showNavbar = !hideNavFooter;
        this.showFooter = !hideNavFooter;
      }
    });
  }
}
