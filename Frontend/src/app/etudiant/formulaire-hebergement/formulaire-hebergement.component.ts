import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { FormulaireSharedService } from '../../services/formulaire-shared.service';
import { HebergementService } from '../../services/hebergement.service';
import { EtudiantService } from '../../services/etudiant.service';

@Component({
  selector: 'app-formulaire-hebergement',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent,
  ],
  templateUrl: './formulaire-hebergement.component.html',
  styleUrls: ['./formulaire-hebergement.component.scss']
})
export class FormulaireHebergementComponent implements OnInit {
  sidebarVisible = true;

  form = {
    nom: '',
    prenom: '',
    email: '',
    telephone: '',
    nationalite: '',
    identite: '',
    etablissement: '',
    ville: '',
    dateArrivee: '',
    typeHebergement: '',
    admission: null as File | null,
    identiteFile: null as File | null,
    commentaires: ''
  };

  documents: File[] = [];

  constructor(
     private formulaireShared: FormulaireSharedService,
     private hebergementService: HebergementService,
     private etudiantService: EtudiantService,
) {}

ngOnInit(): void {
  this.etudiantService.getProfil().subscribe({
    next: (etudiant) => {
      this.form.nom = etudiant.nom;
      this.form.prenom = etudiant.prenom;
      this.form.email = etudiant.email;
      this.form.telephone = etudiant.telephone;
      this.form.nationalite = etudiant.nationalite;
      this.form.identite = etudiant.numeroIdentite;
    },
    error: (err: any) => {
      console.error('Erreur lors de la récupération du profil étudiant :', err);
    }
  });
}


  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

 uploadDocuments(event: any): void {
    const selectedFiles: File[] = Array.from(event.target.files);
    this.documents.push(...selectedFiles);
  }

  supprimerDocument(doc: File): void {
    this.documents = this.documents.filter(d => d !== doc);
  }

 onSubmit(formRef: NgForm): void {
   if (formRef.valid) {
     const formData = new FormData();
     formData.append('ville', this.form.ville);
     formData.append('dateArrivee', this.form.dateArrivee);
     formData.append('typeHebergement', this.form.typeHebergement);
     formData.append('commentaires', this.form.commentaires);

     this.documents.forEach(doc => {
       formData.append('fichiers', doc);
     });

     this.hebergementService.envoyerDemande(formData).subscribe({
       next: () => {
         alert('Demande envoyée avec succès !');
         formRef.resetForm();
         this.documents = [];
       },
       error: (err: any) => {
         alert("Erreur lors de l'envoi : " + (err.error?.message || err.message || 'Erreur inconnue'));
       }
     });
   } else {
     alert('Formulaire invalide.');
   }
 }



}
