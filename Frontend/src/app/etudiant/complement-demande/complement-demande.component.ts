import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';

@Component({
  selector: 'app-complement-demande',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent,
    SidebarEvenementsComponent,
    FooterEtudiantComponent
  ],
  templateUrl: './complement-demande.component.html',
  styleUrls: ['./complement-demande.component.scss']
})
export class ComplementDemandeComponent implements OnInit {
  complementForm!: FormGroup;
  remarque: string = '';
  sidebarVisible = true;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  ngOnInit(): void {
    this.complementForm = this.fb.group({
      justificatif: [null, Validators.required],
      commentaire: ['']
    });

    // Exemple : affichage de remarque statique ou récupérée autrement
    this.remarque = 'Veuillez déposer les documents demandés.';
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.complementForm.patchValue({ justificatif: file });
    }
  }

  onSubmit(): void {
    if (this.complementForm.valid) {
      console.log('Complément envoyé :', this.complementForm.value);
      alert('Votre complément a été envoyé avec succès.');
      this.router.navigate(['/etat-demande']);
    }
  }
}
