import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-conditions-generales',
  standalone: true,
  imports: [CommonModule, RouterLink, FooterComponent, NavbarComponent],
  templateUrl: './conditions-generales.component.html',
  styleUrls: ['./conditions-generales.component.scss']
})
export class ConditionsGeneralesComponent {
  currentDate = new Date().toLocaleDateString('fr-FR');
}
