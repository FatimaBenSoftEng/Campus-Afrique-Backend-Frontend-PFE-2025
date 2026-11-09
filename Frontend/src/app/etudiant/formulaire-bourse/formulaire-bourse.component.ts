import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { FormulaireSharedService } from '../../services/formulaire-shared.service';
import { BourseService } from '../../services/bourse.service';
import { EtudiantService } from '../../services/etudiant.service';

@Component({
  selector: 'app-formulaire-bourse',
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
  templateUrl: './formulaire-bourse.component.html',
  styleUrls: ['./formulaire-bourse.component.scss']
})
export class FormulaireBourseComponent implements OnInit {
  sidebarVisible = true;

  form = {
    nom: '',
    prenom: '',
    dateNaissance: '',
    nationalite: '',
    identite: '',
    numeroEtudiant: '',
    email: '',
    telephone: '',
    adresse: '',
    dernierDiplome: '',
    anneeObtention: '',
    specialite: '',
    anneeUniversitaire: '',
    etablissementCible: '',
    typeBourse: '',
    moyenne: '',
    organismeBourseId: '',
  };

  documents: File[] = [];
  organismes: any[] = [];

  constructor(
    private formulaireShared: FormulaireSharedService,
    private bourseService: BourseService,
    private etudiantService: EtudiantService
  ) {}

  ngOnInit(): void {
    this.etudiantService.getProfil().subscribe({
      next: (data: any) => {
        this.form.nom = data.nom || '';
        this.form.prenom = data.prenom || '';
        this.form.email = data.email || '';
        this.form.telephone = data.telephone || '';
        this.form.dateNaissance = data.dateNaissance || '';
        this.form.nationalite = data.nationalite || '';
        this.form.adresse = data.adresse || '';
        this.form.identite = data.numeroIdentite || '';
        this.form.numeroEtudiant = data.matricule || '';
        this.form.specialite = data.specialite || '';
        this.form.anneeObtention = data.anneeObtention || '';
        this.form.dernierDiplome = data.dernierDiplome || '';
        this.form.moyenne = data.moyenne || '';
      },
      error: (err: any) => {
        console.error("Erreur lors du chargement de l'étudiant :", err);
      }
    });

    this.bourseService.getOrganismesBourse().subscribe({
      next: (data) => this.organismes = data,
      error: (err) => console.error("Erreur lors du chargement des organismes :", err)
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  onSubmit(formData: NgForm): void {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^[0-9]{8,15}$/;

    if (!emailRegex.test(this.form.email)) {
      alert("Format d'email invalide.");
      return;
    }

    if (!phoneRegex.test(this.form.telephone)) {
      alert("Le numéro de téléphone doit contenir entre 8 et 15 chiffres.");
      return;
    }

    if (formData.valid) {
      const dataToSend = new FormData();

      // 🎯 Envoi des champs dans l'ordre et noms exacts attendus par le backend
      dataToSend.append('adresse', this.form.adresse);
      dataToSend.append('numeroEtudiant', this.form.numeroEtudiant);
      dataToSend.append('email', this.form.email);
      dataToSend.append('telephone', this.form.telephone);

      dataToSend.append('diplome', this.form.dernierDiplome);
      dataToSend.append('anneeDiplome', this.form.anneeObtention);
      dataToSend.append('filiere', this.form.specialite);
      dataToSend.append('anneeUniversitaire', this.form.anneeUniversitaire);
      dataToSend.append('etablissementCible', this.form.etablissementCible);
      dataToSend.append('typeBourse', this.form.typeBourse);
      dataToSend.append('moyenne', this.form.moyenne.toString());

      // Important : transforme en string pour éviter erreur 400
      dataToSend.append('organismeBourseId', this.form.organismeBourseId.toString());

      // ✅ Ajoute les fichiers sélectionnés
      this.documents.forEach(file => {
        dataToSend.append('fichiers', file);
      });

      // 🔎 Debug : afficher les valeurs
      for (let pair of dataToSend.entries()) {
        console.log(`${pair[0]}:`, pair[1]);
      }

      this.bourseService.envoyerFormulaire(dataToSend).subscribe({
        next: (res) => {
          alert('✅ Demande de bourse envoyée avec succès.');
          formData.resetForm();
          this.documents = [];
        },
        error: (err: any) => {
          console.error('❌ Erreur lors de l’envoi de la demande :', err);
          alert('Une erreur est survenue lors de la soumission.');
        }
      });
    } else {
      console.warn('Formulaire invalide');
    }
  }

  uploadDocuments(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files) return;

    const allowedTypes = [
      'application/pdf',
      'application/msword',
      'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      'image/jpeg',
      'image/png'
    ];

    for (let i = 0; i < input.files.length; i++) {
      const file = input.files[i];
      if (!allowedTypes.includes(file.type)) {
        alert(`Type de fichier non supporté : ${file.name}`);
        continue;
      }
      this.documents.push(file);
    }

    console.log("📂 Fichiers ajoutés :", this.documents);
  }

  supprimerDocument(doc: File): void {
    this.documents = this.documents.filter(f => f !== doc);
  }
}
