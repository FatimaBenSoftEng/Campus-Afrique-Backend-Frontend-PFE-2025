import { Component, OnInit } from '@angular/core';
import { BourseService } from '../../services/bourse.service';
import { CommonModule } from '@angular/common';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-etat-demande-bourse',
  standalone: true,
  imports: [
    CommonModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './etat-demande-bourse.component.html',
  styleUrls: ['./etat-demande-bourse.component.scss']
})
export class EtatDemandeBourseComponent implements OnInit {
  sidebarVisible = true;
  demandes: any[] = [];

  constructor(private bourseService: BourseService) {}

  ngOnInit(): void {
    this.bourseService.getMesDemandes().subscribe({
      next: (data) => this.demandes = data,
      error: (err) => console.error('Erreur chargement demandes bourse:', err)
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
