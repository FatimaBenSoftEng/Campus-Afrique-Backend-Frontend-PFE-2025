import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { DocumentService, DocumentDTO } from '../../services/document.service';
@Component({
  selector: 'app-documents',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.scss']
})
export class DocumentsComponent implements OnInit {
  sidebarVisible = true;
  activeTab: 'uploads' | 'attestations' = 'uploads';
  documentsUploades: DocumentDTO[] = [];
  attestations: DocumentDTO[] = [];

  constructor(private documentService: DocumentService) {}

  ngOnInit(): void {
    this.documentService.getDocuments().subscribe({
      next: (docs) => {
        this.documentsUploades = docs.filter(d => d.statut !== 'ATTESTATION');
        this.attestations = docs.filter(d => d.statut === 'ATTESTATION');
      },
      error: (err) => console.error('Erreur chargement documents', err)
    });
  }

  toggleSidebar() {
    this.sidebarVisible = !this.sidebarVisible;
  }

  switchTab(tab: 'uploads' | 'attestations') {
    this.activeTab = tab;
  }

  telecharger(doc: DocumentDTO) {
    window.open('http://localhost:8080/' + doc.cheminFichier, '_blank');
  }
}
