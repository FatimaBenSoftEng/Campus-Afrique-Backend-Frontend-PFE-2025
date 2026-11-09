import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransportService } from '../../services/transport.service';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-etat-demande-transport',
  standalone: true,
  imports: [
    CommonModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    FooterEtudiantComponent,
  ],
  templateUrl: './etat-demande-transport.component.html',
  styleUrls: ['./etat-demande-transport.component.scss']
})
export class EtatDemandeTransportComponent implements OnInit {
  sidebarVisible = true;
  demandes: any[] = [];

  constructor(private transportService: TransportService) {}

  ngOnInit(): void {
    this.transportService.getDemandes().subscribe({
      next: (data: any[]) => {
        this.demandes = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des demandes :', err);
      }
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }
}
