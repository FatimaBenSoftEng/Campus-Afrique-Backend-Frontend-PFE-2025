import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { FormulaireSharedService } from '../../services/formulaire-shared.service';
import { VisaService } from '../../services/visa.service';
import { EtudiantService } from '../../services/etudiant.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-formulaire-visa',
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
  templateUrl: './formulaire-visa.component.html',
  styleUrls: ['./formulaire-visa.component.scss']
})
export class FormulaireVisaComponent implements OnInit {
  sidebarVisible = true;

  paysList: string[] = ['Maroc', 'Sénégal', 'Côte d’Ivoire', 'Tunisie', 'Mali'];
  ambassades: any[] = [];


 form = {
   // Infos personnelles
   nom: '',
   prenom: '',
   nationalite: '',
   sexe: '',
   email: '',
   telephone: '',
   identite: '',

   // Séjour d'études
   paysDestination: '',
   ville: '',
   etablissement: '',
   dateDepart: '',
   duree: '',
   adresse: '',

   // Garant
   nomGarant: '',
   lien: '',
   adresseGarant: '',
   telephoneGarant: '',
   professionGarant: '',
   revenu: null as number | null,

   // 🟨 Manquant → à ajouter :
   commentaires: '',
   ambassadeId: ''
 };

  documents: File[] = [];

  constructor(
    private formulaireShared: FormulaireSharedService,
    private visaService: VisaService,
    private etudiantService: EtudiantService,
    private http: HttpClient // ✅ AJOUT ICI
  ) {}


 ngOnInit(): void {
   this.etudiantService.getProfil().subscribe({
     next: (etudiant) => {
       this.form.nom = etudiant.nom;
       this.form.prenom = etudiant.prenom;
       this.form.nationalite = etudiant.nationalite;
       this.form.sexe = etudiant.genre;
       this.form.email = etudiant.email;
       this.form.telephone = etudiant.telephone;
       this.form.identite = etudiant.numeroIdentite;
     },
     error: (err) => {
       console.error("Erreur chargement étudiant", err);
     }
   });

   this.http.get<any[]>('http://localhost:8080/api/agents-ambassade/all').subscribe({
     next: (data) => {
       this.ambassades = data;
     },
     error: (err) => {
       console.error('Erreur chargement ambassades', err);
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
    if (!formRef.valid) {
      alert("Formulaire invalide.");
      return;
    }

    const formData = new FormData();
    formData.append("paysDestination", this.form.paysDestination);
    formData.append("ville", this.form.ville);
    formData.append("etablissement", this.form.etablissement);
    formData.append("dateDepart", this.form.dateDepart);
    formData.append("duree", this.form.duree);
    formData.append("adresse", this.form.adresse);
    formData.append("nomGarant", this.form.nomGarant);
    formData.append("lien", this.form.lien);
    formData.append("adresseGarant", this.form.adresseGarant);
    formData.append("telephoneGarant", this.form.telephoneGarant);
    formData.append("professionGarant", this.form.professionGarant);
    formData.append("ambassadeId", this.form.ambassadeId);
    if (this.form.revenu !== null) {
      formData.append("revenu", this.form.revenu.toString());
    }
    formData.append("commentaires", this.form.commentaires || '');

    this.documents.forEach(file => formData.append("fichiers", file));

    this.visaService.envoyerDemande(formData).subscribe({
      next: (res) => {
        alert(res.message || "Demande envoyée avec succès !");
        formRef.resetForm();
        this.documents = [];
      },
      error: (err) => {
        alert("Erreur lors de l'envoi : " + (err.error?.message || err.message));
      }
    });
  }

}
