import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-slidebar-etablissement-fix',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './slidebar-etablissement-fix.component.html',
  styleUrl: './slidebar-etablissement-fix.component.scss'
})
export class SlidebarEtablissementFixComponent implements OnInit {
  menuDemandesOuvertes: boolean = false;
    menuCandidatureOuvertes: boolean = false;
    menuPaiementsOuvertes: boolean = false;

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
    togglePaiements() {
      this.menuPaiementsOuvertes = !this.menuPaiementsOuvertes;
      // Fermer les autres menus quand on ouvre celui-ci (optionnel)
      if (this.menuPaiementsOuvertes) {
        this.menuDemandesOuvertes = false;
        this.menuCandidatureOuvertes = false;
      }
    }

    logout(): void {
      localStorage.clear();
      this.router.navigate(['/auth/login']);
    }

}
