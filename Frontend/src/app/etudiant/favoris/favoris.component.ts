import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EtablissementsListService, EtablissementListItem } from '../../services/etablissements-list.service';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-favoris',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './favoris.component.html',
  styleUrl: './favoris.component.scss'
})
export class FavorisComponent implements OnInit {
  favoris: EtablissementListItem[] = [];
  sidebarVisible = true;

  constructor(private etabService: EtablissementsListService) {}

  ngOnInit(): void {
    this.etabService.getFavoris().subscribe(data => {
      this.favoris = data;
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  retirerFavori(id: number): void {
    this.etabService.toggleFavori(id);
    this.favoris = this.favoris.filter(e => e.id !== id);
  }
}
