import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-active-formation',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    SlidebarEtablissementFixComponent,
    NavbarEtablissementComponent,
    FooterEtablissementComponent,
    SidebarEvenementsComponent
  ],
  templateUrl: './active-formation.component.html',
  styleUrl: './active-formation.component.scss'
})
export class ActiveFormationComponent {
  sidebarVisible = true;
  toggleSidebar() {
    this.sidebarVisible = !this.sidebarVisible;
  }

  // Tableau des formations actives (simulation)
  formations = [
    { id: 1, nom: 'Formation Angular', description: 'Apprentissage du framework Angular', duree: 1, frais: '5000 MAD', statut: 'Active' },
    { id: 2, nom: 'Formation React', description: 'Apprentissage du framework React', duree: 2, frais: '6000 MAD', statut: 'Active' }
  ];
}
