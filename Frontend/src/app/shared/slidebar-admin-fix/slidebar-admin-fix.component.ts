import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-slidebar-admin-fix',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './slidebar-admin-fix.component.html',
  styleUrl: './slidebar-admin-fix.component.scss'
})
export class SlidebarAdminFixComponent implements OnInit {
   menuDemandesOuvertes: boolean = false;
    menuCandidatureOuvertes: boolean = false;

    constructor(private router: Router) {}

    ngOnInit(): void {
      // ✅ Charger l'état du menu depuis localStorage
      this.menuDemandesOuvertes = localStorage.getItem('menuDemandes') === 'true';
      this.menuCandidatureOuvertes = localStorage.getItem('menuCandidature') === 'true';
    }

    toggleDemandes(): void {
      this.menuDemandesOuvertes = !this.menuDemandesOuvertes;
      localStorage.setItem('menuDemandes', this.menuDemandesOuvertes.toString());
    }

    toggleCandidature(): void {
      this.menuCandidatureOuvertes = !this.menuCandidatureOuvertes;
      localStorage.setItem('menuCandidature', this.menuCandidatureOuvertes.toString());
    }

    logout(): void {
      localStorage.clear();
      this.router.navigate(['/auth/login']);
    }

}
