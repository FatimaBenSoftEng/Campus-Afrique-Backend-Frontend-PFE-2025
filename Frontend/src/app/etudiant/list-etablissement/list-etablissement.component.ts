import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { EtablissementsListService, EtablissementListItem } from '../../services/etablissements-list.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-list-etablissement',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent,
    FormsModule,
    NgxPaginationModule
  ],
  templateUrl: './list-etablissement.component.html',
  styleUrl: './list-etablissement.component.scss'
})

export class ListEtablissementComponent implements OnInit {

  etablissements: EtablissementListItem[] = [];
  filteredEtablissements: EtablissementListItem[] = [];

  currentPage = 1;
  itemsPerPage = 10;
  sidebarVisible = true;

  currentSearchQuery = '';

  selectedPays: string = '';
  selectedType: string = '';
  selectedFormation: string = '';

  paysDisponibles: string[] = [];
  typeDisponibles: string[] = [];
  formationsDisponibles: string[] = [];

  constructor(
    private etablissementsListService: EtablissementsListService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.etablissementsListService.getEtablissements().subscribe((data: EtablissementListItem[]) => {
      this.etablissements = data;
      this.filteredEtablissements = [...data];

      // ✅ Correction ici
      this.paysDisponibles = [...new Set(data.map(e => e.pays).filter((p): p is string => !!p))];

      this.typeDisponibles = [...new Set(data.map(e => e.type).filter((t): t is string => !!t))];

      // ✅ Correction ici aussi (avec typage)
      this.formationsDisponibles = [...new Set(
        data.flatMap(e => (e.formations || []).map((f: any) => f.nom)).filter((f): f is string => !!f)
      )];
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  voirDetail(id: number): void {
    this.router.navigate(['/etablissements', id]);
  }

  toggleFavori(id: number): void {
    this.etablissementsListService.toggleFavori(id);
    this.applySearchAndFilters();
  }

  handleSearch(query: string): void {
    this.currentSearchQuery = query.toLowerCase();
    this.applySearchAndFilters();
    this.currentPage = 1;
  }

  applySearchAndFilters(): void {
    let result = [...this.etablissements];

    // Recherche
    if (this.currentSearchQuery.trim()) {
      result = result.filter(etab =>
        etab.nom?.toLowerCase().includes(this.currentSearchQuery) ||
        etab.nomAbr?.toLowerCase().includes(this.currentSearchQuery) ||
        etab.ville?.toLowerCase().includes(this.currentSearchQuery) ||
        etab.pays?.toLowerCase().includes(this.currentSearchQuery) ||
        etab.description?.toLowerCase().includes(this.currentSearchQuery)
      );
    }

    // Filtrage pays
    if (this.selectedPays) {
      result = result.filter(e => e.pays === this.selectedPays);
    }

  // Filtrage type
      if (this.selectedType) {
        result = result.filter(e => e.type === this.selectedType);
      }

    // Filtrage formation
    if (this.selectedFormation) {
      result = result.filter(e =>
        (e.formations || []).some((f: any) => f.nom === this.selectedFormation)
      );
    }

    this.filteredEtablissements = result;
    this.currentPage = 1;
  }

  resetSearchAndFilters(): void {
    this.currentSearchQuery = '';
    this.selectedPays = '';
    this.selectedType = '';
    this.selectedFormation = '';
    this.filteredEtablissements = [...this.etablissements];
    this.currentPage = 1;
  }

  get totalItems(): number {
    return this.filteredEtablissements.length;
  }
}
