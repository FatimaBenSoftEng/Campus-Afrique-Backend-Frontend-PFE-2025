import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { EtudiantService } from '../../services/etudiant.service';

@Component({
  selector: 'app-formulaire-profil',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    SidebarEtudiantFixComponent,
    NavbarEtudiantComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './formulaire-profil.component.html',
  styleUrls: ['./formulaire-profil.component.scss']
})
export class FormulaireProfilComponent implements OnInit {
  form: any = {};
  photoPreview: string | ArrayBuffer | null = null;
  sidebarVisible = true;
  etudiantId: number | null = null;
  today: string = new Date().toISOString().split('T')[0];

  constructor(private etudiantService: EtudiantService) {}

ngOnInit(): void {
    this.etudiantService.getProfil().subscribe({
      next: (etudiant) => {
        this.etudiantId = etudiant.id;
        this.form = {
          nom: etudiant.nom || '',
          prenom: etudiant.prenom || '',
          email: etudiant.email || '',
          telephone: etudiant.telephone || '',
          dateNaissance: etudiant.dateNaissance || '',
          nationalite: etudiant.nationalite || '',
          adresse: etudiant.adresse || '',
          paysOrigine: etudiant.paysOrigine || '',
          genre: etudiant.genre || '',
          dernierDiplome: etudiant.dernierDiplome || '',
          specialite: etudiant.specialite || '',
          anneeObtention: etudiant.anneeObtention || '',
          moyenne: etudiant.moyenne || '',
          photo: null,
          photoProfil: etudiant.photoProfil || ''
        };

        const image = this.form.photoProfil?.trim();
        this.photoPreview = image && image !== ''
          ? `http://localhost:8080/uploads/${image}`
          : 'img/default-avatar.png';
      },
      error: () => {
        alert("Erreur de chargement du profil étudiant");
      }
    });
  }

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  onPhotoChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (file) {
      this.form.photo = file;

      const reader = new FileReader();
      reader.onload = () => {
        this.photoPreview = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(formRef: NgForm): void {
    if (!formRef.valid || !this.etudiantId) {
      alert('Formulaire invalide.');
      return;
    }

    const formData = new FormData();
    formData.append("nom", this.form.nom);
    formData.append("prenom", this.form.prenom);
    formData.append("email", this.form.email);
    formData.append("telephone", this.form.telephone);
    formData.append("dateNaissance", this.form.dateNaissance);
    formData.append("nationalite", this.form.nationalite);
    formData.append("adresse", this.form.adresse);
    formData.append("paysOrigine", this.form.paysOrigine);
    formData.append("genre", this.form.genre);
    formData.append("dernierDiplome", this.form.dernierDiplome);
    formData.append("specialite", this.form.specialite);
    formData.append("anneeObtention", this.form.anneeObtention);
    formData.append("moyenne", this.form.moyenne);
    formData.append("typeIdentite", this.form.typeIdentite); // ✅ À ajouter
    formData.append("numeroIdentite", this.form.numeroIdentite); // ✅ À ajouter
    formData.append("matricule", this.form.matricule); // ✅ À ajouter
    if (this.form.photo) {
      formData.append("photo", this.form.photo);
    }

    this.etudiantService.updateProfilFormData(this.etudiantId, formData).subscribe({
      next: () => alert("Profil mis à jour avec succès"),
     error: (err) => {
       console.error("Erreur détaillée :", err);
       alert("Erreur lors de la mise à jour : " + (err?.error?.message || 'Inconnue'));
     }
    });
  }


}
