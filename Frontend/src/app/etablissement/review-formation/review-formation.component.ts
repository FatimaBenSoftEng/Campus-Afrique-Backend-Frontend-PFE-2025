import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-review-formation',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    SlidebarEtablissementFixComponent,
    NavbarEtablissementComponent,
    FooterEtablissementComponent,
    SidebarEvenementsComponent
  ],
  templateUrl: './review-formation.component.html',
  styleUrl: './review-formation.component.scss'
})
export class ReviewFormationComponent {
  sidebarVisible = true;
  toggleSidebar() {
    this.sidebarVisible = !this.sidebarVisible;
  }

  // Tableau des reviews simulées
  reviews = [
    { id: 1, nom: 'Angular avancé', review: 'Très bonne formation', statut: 'Inactive' },
    { id: 2, nom: 'React débutant', review: 'Bonne pédagogie', statut: 'Active' }
  ];
}
