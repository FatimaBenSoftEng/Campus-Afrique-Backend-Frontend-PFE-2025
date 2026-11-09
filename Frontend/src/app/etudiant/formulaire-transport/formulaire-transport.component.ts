import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { FormulaireSharedService } from '../../services/formulaire-shared.service';
import { TransportService } from '../../services/transport.service';
import { EtudiantService } from '../../services/etudiant.service';

@Component({
  selector: 'app-formulaire-transport',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './formulaire-transport.component.html',
  styleUrls: ['./formulaire-transport.component.scss']
})
export class FormulaireTransportComponent implements OnInit {
  sidebarVisible = true;

  form = {
    nom: '',
    prenom: '',
    nationalite: '',
    identite: '',
    email: '',
    telephone: '',
    ville: '',
    adresse: '',
    etablissement: '',
    frequence: '',
    typeTransport: '',
    besoinAbonnement: false,
    justificatif: null as File | null,
    identiteFile: null as File | null,
    commentaires: ''
  };

  documents: File[] = [];

  constructor(private formulaireShared: FormulaireSharedService,
              private transportService: TransportService,
              private etudiantService: EtudiantService) {}

 ngOnInit(): void {
   this.etudiantService.getProfil().subscribe({
     next: (etudiant) => {
       this.form.nom = etudiant.nom;
       this.form.prenom = etudiant.prenom;
       this.form.nationalite = etudiant.nationalite;
       this.form.identite = etudiant.numeroIdentite; // ou CIN/Passeport
       this.form.email = etudiant.email;
       this.form.telephone = etudiant.telephone;
     },
     error: (err) => {
       console.error('Erreur lors du chargement du profil étudiant', err);
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

  supprimerDocument(file: File): void {
    this.documents = this.documents.filter(f => f !== file);
  }

 onSubmit(formRef: NgForm): void {
   if (formRef.valid) {
     const formData = new FormData();
     formData.append('ville', this.form.ville);
     formData.append('adresse', this.form.adresse);
     formData.append('frequence', this.form.frequence);
     formData.append('typeTransport', this.form.typeTransport);
     formData.append('besoinAbonnement', String(this.form.besoinAbonnement));
     formData.append('commentaires', this.form.commentaires);

     this.documents.forEach(file => formData.append('fichiers', file));

     this.transportService.envoyerDemande(formData).subscribe({
       next: (res) => {
         alert(res.message || 'Demande envoyée avec succès !');
         formRef.resetForm();
         this.documents = [];
       },
       error: (err) => {
         alert("Erreur lors de l'envoi : " + (err.error?.message || err.message || 'Erreur inconnue'));
       }
     });
   } else {
     alert('Formulaire invalide.');
   }
 }

}
