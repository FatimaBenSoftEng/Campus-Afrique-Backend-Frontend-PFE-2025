import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EtablissementDetailService, EtablissementDetail } from '../../services/etablissement-detail.service';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detail-etablissement',
  standalone: true,
  imports: [
    CommonModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './detail-etablissement.component.html',
  styleUrls: ['./detail-etablissement.component.scss']
})
export class DetailEtablissementComponent implements OnInit {
  etablissement!: EtablissementDetail;
  sidebarVisible = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private detailService: EtablissementDetailService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.detailService.getEtablissementById(id).subscribe((data: EtablissementDetail | undefined) => {
      if (data) {
        this.etablissement = data;
            console.log("Chemin image reçu :", this.etablissement.imageUrl);

      } else {
        this.router.navigate(['/not-found']);
      }
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  postuler(): void {
    this.router.navigate(['/formulaire-preinscription'], {
      queryParams: {
        etabId: this.etablissement.id,
        etabNom: this.etablissement.nom
      }
    });
  }

  getAnciennete(dateCreation: string): number {
    if (!dateCreation) return 0;
    const creationYear = new Date(dateCreation).getFullYear();
    const currentYear = new Date().getFullYear();
    return currentYear - creationYear;
  }

  getAncienneteMessage(): string {
    const age = this.getAnciennete(this.etablissement?.dateCreation || '');
    return age >= 10 ? 'Établissement ancien' : 'Établissement récent';
  }

}
