import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { FormulaireSharedService } from '../../services/formulaire-shared.service';
import { PreinscriptionService } from '../../services/preinscription.service';
import { EtudiantService } from '../../services/etudiant.service';
import { FormationService, Formation } from '../../services/formation.service';

@Component({
  selector: 'app-formulaire-preinscription',
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
  templateUrl: './formulaire-preinscription.component.html',
  styleUrls: ['./formulaire-preinscription.component.scss']
})
export class FormulairePreinscriptionComponent implements OnInit {
  sidebarVisible = true;
  filieres: Formation[] = [];
  documents: File[] = [];

  form: any = {
    nom: '', prenom: '', genre: '', dateNaissance: '', nationalite: '', paysResidence: '',
    adresse: '', email: '', telephone: '', typePiece: '', numeroPiece: '',
    matricule: '', dernierDiplome: '', specialite: '', anneeObtention: '', paysObtention: '',
    moyenne: '', niveau: '', etablissementId: '', etablissementNom: '', formationId: null
  };

  private phoneRegex = /^[0-9]{8,15}$/;
  private emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  constructor(
    private route: ActivatedRoute,
    private formulaireShared: FormulaireSharedService,
    private preinscriptionService: PreinscriptionService,
    private etudiantService: EtudiantService,
    private formationService: FormationService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.form.etablissementId = params['etabId'] || '';
      this.form.etablissementNom = params['etabNom'] || '';

      if (this.form.etablissementId) {
        this.formationService.getFormationsByEtablissement(this.form.etablissementId).subscribe({
          next: (formations) => this.filieres = formations,
          error: () => this.filieres = []
        });
      }
    });

    const sharedData = this.formulaireShared.getFormData();
    this.form.nom = sharedData.nom || '';
    this.form.prenom = sharedData.prenom || '';
    this.form.email = sharedData.email || '';
    this.form.telephone = sharedData.telephone || '';
    this.form.dateNaissance = sharedData.dateNaissance || '';
    this.form.nationalite = sharedData.nationalite || '';
    this.form.adresse = sharedData.adresse || '';
    this.form.dernierDiplome = sharedData.dernierDiplome || '';

    this.etudiantService.getProfil().subscribe((etudiant: any) => {
      this.form.nom = etudiant.nom;
      this.form.prenom = etudiant.prenom;
      this.form.email = etudiant.email;
      this.form.telephone = etudiant.telephone;
      this.form.dateNaissance = etudiant.dateNaissance?.split('T')[0] || '';
      this.form.nationalite = etudiant.nationalite;
      this.form.genre = etudiant.genre;
      this.form.adresse = etudiant.adresse || '';
      this.form.dernierDiplome = etudiant.dernierDiplome || '';
      this.form.specialite = etudiant.specialite || '';
      this.form.moyenne = etudiant.moyenne?.toString() || '';
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
    this.documents = this.documents.filter(f => f !== doc);
  }

  validateEmail(email: string): boolean {
    return this.emailRegex.test(email);
  }

  validateTelephone(telephone: string): boolean {
    return this.phoneRegex.test(telephone);
  }

  onSubmit(formRef: NgForm): void {
    if (!this.validateEmail(this.form.email)) {
      alert("Format d'email invalide.");
      return;
    }

    if (!this.validateTelephone(this.form.telephone)) {
      alert("Le numéro de téléphone doit contenir entre 8 et 15 chiffres.");
      return;
    }

    if (formRef.valid) {
      const formData = new FormData();

      Object.keys(this.form).forEach(key => {
        const value = this.form[key];
        if (value !== null && value !== undefined && value !== '') {
          if (key === 'moyenne') {
            formData.append(key, parseFloat(value).toString());
          } else if (key === 'anneeObtention') {
            formData.append(key, parseInt(value, 10).toString());
          } else {
            formData.append(key, value);
          }
        }
      });

      this.documents.forEach(file => {
        formData.append('fichiers', file, file.name);
      });

     this.preinscriptionService.envoyerFormulaire(formData).subscribe({
       next: (response: any) => {
         console.log("Réponse backend :", response);
         if (response.message && response.message.includes('succès')) {
           alert("Préinscription envoyée avec succès !");
         } else {
           alert("Erreur inattendue.");
         }
       },
       error: (err: any) => {
         alert("Erreur lors de l’envoi du formulaire.");
         console.error(err);
       }
     });

    }
  }

  resetFormFields(): void {
    this.form = {
      nom: '', prenom: '', genre: '', dateNaissance: '', nationalite: '', paysResidence: '',
      adresse: '', email: '', telephone: '', typePiece: '', numeroPiece: '',
      matricule: '', dernierDiplome: '', specialite: '', anneeObtention: '', paysObtention: '',
      moyenne: '', niveau: '', etablissementId: '', etablissementNom: '', formationId: null
    };
    this.filieres = [];
    this.documents = [];
  }
}
