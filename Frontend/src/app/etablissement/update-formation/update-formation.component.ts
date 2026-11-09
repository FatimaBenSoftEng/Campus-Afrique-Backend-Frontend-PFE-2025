import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-formation',
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    SlidebarEtablissementFixComponent,
    NavbarEtablissementComponent,
    FooterEtablissementComponent,
    SidebarEvenementsComponent
  ],
  templateUrl: './update-formation.component.html',
  styleUrl: './update-formation.component.scss'
})
export class UpdateFormationComponent {
  sidebarVisible = true;
  toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }

  // Formation sélectionnée à modifier
  selectedFormation = {
    id: 1,
    nom: 'Formation Angular',
    description: 'Apprentissage du framework Angular',
    duree: 1,
    frais: '5000 MAD',
    statut: 'Active'
  };

  // Méthode appelée lors de la soumission du formulaire
  updateFormation() {
    console.log('Formation mise à jour :', this.selectedFormation);
    alert('Formation mise à jour avec succès ✅');
  }

  cancel() {
    console.log('Modification annulée');
    alert('Modification annulée ❌');
  }
}
