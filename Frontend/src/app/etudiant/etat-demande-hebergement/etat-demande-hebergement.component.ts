import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HebergementService } from '../../services/hebergement.service';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-etat-demande-hebergement',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './etat-demande-hebergement.component.html',
  styleUrls: ['./etat-demande-hebergement.component.scss']
})
export class EtatDemandeHebergementComponent implements OnInit {
  sidebarVisible = true;
  demandes: any[] = [];

  constructor(private hebergementService: HebergementService) {}

  ngOnInit(): void {
    this.chargerDemandes();
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  chargerDemandes(): void {
    this.hebergementService.getDemandes().subscribe({
      next: (data: any[]) => {
        this.demandes = data;
      },
      error: (err: any) => {
        console.error('Erreur lors de la récupération des demandes :', err);
      }
    });
  }
}
