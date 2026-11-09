import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-footer-etablissement',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './footer-etablissement.component.html',
  styleUrl: './footer-etablissement.component.scss'
})
export class FooterEtablissementComponent {

}
