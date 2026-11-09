import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MiniCalendarComponent } from '../../shared/mini-calendar/mini-calendar.component';

@Component({
  selector: 'app-sidebar-evenements',
  standalone: true,
  imports: [CommonModule, RouterModule, MiniCalendarComponent],
  templateUrl: './sidebar-evenements.component.html',
  styleUrls: ['./sidebar-evenements.component.scss']
})
export class SidebarEvenementsComponent {
  // Exemple d'événements pour le calendrier
  calendarEvents = [
    { date: new Date(2024, 4, 10), label: "Ouverture inscriptions - Ecole X" },
    { date: new Date(2024, 4, 15), label: "Journée portes ouvertes - Ecole Y" },
    // Ajoute dynamiquement depuis le backend
  ];

  // Exemple d'offres/publications
  offres = [
    {
      titre: "Bourse d'excellence 2024",
      ecole: "Université de Dakar",
      date: new Date(2024, 4, 8),
      description: "Ouverture des candidatures pour la bourse d'excellence.",
      lien: "https://exemple.com/bourse"
    },
    {
      titre: "Nouvelle formation en Data Science",
      ecole: "ESMT",
      date: new Date(2024, 4, 5),
      description: "Découvrez notre nouveau cursus Data Science.",
      lien: ""
    }
    // Ajoute dynamiquement depuis le backend
  ];

  onDateSelected(event: any) {
    // Affiche les événements du jour sélectionné, ou ouvre un détail
    alert('Événement : ' + event.label);
  }
}
