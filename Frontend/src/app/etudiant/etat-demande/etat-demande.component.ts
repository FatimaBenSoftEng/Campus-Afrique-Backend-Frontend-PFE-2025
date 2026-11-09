import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PreinscriptionService } from '../../services/preinscription.service';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-etat-demande',
  standalone: true,
  imports: [
    CommonModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './etat-demande.component.html',
  styleUrls: ['./etat-demande.component.scss']
})
export class EtatDemandeComponent implements OnInit {
  sidebarVisible = true;
  preinscriptions: any[] = [];
  loading = true;
  error: string | null = null;

  constructor(private preinscriptionService: PreinscriptionService) {}

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  ngOnInit(): void {
    this.preinscriptionService.getMesPreinscriptions().subscribe({
      next: (data) => {
        this.preinscriptions = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement préinscriptions :', err);
        this.error = "Une erreur est survenue lors du chargement.";
        this.loading = false;
      }
    });
  }

  getBadgeClass(statut: string): string {
    switch (statut) {
      case 'EN_ATTENTE': return 'badge bg-warning text-dark';
      case 'VALIDEE': return 'badge bg-success';
      case 'REFUSEE': return 'badge bg-danger';
      case 'COMPLEMENT': return 'badge bg-info text-dark';
      default: return 'badge bg-secondary';
    }
  }
}
