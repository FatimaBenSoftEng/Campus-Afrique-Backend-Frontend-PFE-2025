import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-footer-etudiant',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './footer-etudiant.component.html',
  styleUrl: './footer-etudiant.component.scss'
})
export class FooterEtudiantComponent {

}
