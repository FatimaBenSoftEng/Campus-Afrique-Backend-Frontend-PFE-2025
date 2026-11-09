import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { EtablissementService, Etablissement } from '../../services/etablissement.service';
import { FormsModule } from '@angular/forms'; // ← à ajouter
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-etablissement',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, FormsModule, FooterComponent, NavbarComponent],
  templateUrl: './etablissement.component.html',
  styleUrls: ['./etablissement.component.scss']
})
export class EtablissementComponent implements OnInit {
  etablissements: Etablissement[] = [];
  searchKeyword: string = '';
  typeSelectionne: string = '';
  paysSelectionne: string = '';
  paysDisponibles: string[] = [];
  typesDisponibles: string[] = [];


  constructor(private etablissementService: EtablissementService) {}


 ngOnInit(): void {
   this.etablissementService.getEtablissements().subscribe(data => {
     this.etablissements = data;

     // Extraire dynamiquement les pays sans doublons
     const paysSet = new Set(data.map(e => e.pays));
     this.paysDisponibles = Array.from(paysSet);

     // Extraire dynamiquement les types sans doublons
     const typeSet = new Set(data.map(e => e.type));
     this.typesDisponibles = Array.from(typeSet);
   });
 }

  getAllEtablissements(): void {
    this.etablissementService.getEtablissements().subscribe(data => {
      this.etablissements = data;
    });
  }

  onSearch(): void {
    if (this.searchKeyword.trim()) {
      this.etablissementService.searchEtablissements(this.searchKeyword).subscribe(data => {
        this.etablissements = data;
      });
    } else {
      this.getAllEtablissements();
    }
  }
  onFiltrer(): void {
    this.etablissementService.getEtablissements().subscribe(data => {
      let filtrés = data;

      if (this.typeSelectionne) {
        filtrés = filtrés.filter(e => e.type.includes(this.typeSelectionne));
      }

      if (this.paysSelectionne) {
        filtrés = filtrés.filter(e => e.pays === this.paysSelectionne);
      }

      this.etablissements = filtrés;

      // Met à jour la liste des pays disponibles dynamiquement (évite doublons)
      const paysSet = new Set(data.map(e => e.pays));
      this.paysDisponibles = Array.from(paysSet);
    });
  }

}

