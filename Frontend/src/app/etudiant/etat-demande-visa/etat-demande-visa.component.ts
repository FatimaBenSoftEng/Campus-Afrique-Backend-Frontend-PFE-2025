import { Component, OnInit } from '@angular/core';
import { VisaService } from '../../services/visa.service';
import { CommonModule } from '@angular/common';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-etat-demande-visa',
  standalone: true,
  templateUrl: './etat-demande-visa.component.html',
  styleUrls: ['./etat-demande-visa.component.scss'],
  imports: [
    CommonModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    FooterEtudiantComponent
  ]
})
export class EtatDemandeVisaComponent implements OnInit {
  sidebarVisible = true;
  demandes: any[] = [];

  constructor(private visaService: VisaService) {}

  ngOnInit(): void {
    this.visaService.getMesDemandes().subscribe({
      next: (data) => this.demandes = data,
      error: (err) => console.error('Erreur chargement demandes visa:', err)
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  getBadgeClass(statut: string): string {
    switch (statut) {
      case 'VALIDEE': return 'etat-badge validee';
      case 'REFUSEE': return 'etat-badge refusee';
      case 'EN_ATTENTE': return 'etat-badge en_attente';
      default: return 'etat-badge';
    }
  }
}
