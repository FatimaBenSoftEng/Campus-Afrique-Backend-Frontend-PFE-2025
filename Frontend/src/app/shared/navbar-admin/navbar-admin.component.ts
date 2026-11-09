import { Component, EventEmitter, HostListener, Output,OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // ✅ nécessaire pour ngModel
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './navbar-admin.component.html',
  styleUrl: './navbar-admin.component.scss'
})
export class NavbarAdminComponent implements OnInit{
    @Output() toggleGauche = new EventEmitter<void>();

    showNotificationDropdown = false;
    showUserDropdown = false;
    searchQuery: string = '';
    utilisateurNomComplet: string = 'Nom Prénom'; // valeur par défaut
    photoProfilUrl: string = 'assets/img/default-avatar.png';

    ngOnInit(): void {
      const etudiantString = localStorage.getItem('etudiant');
      if (etudiantString) {
        try {
          const etudiant = JSON.parse(etudiantString);
          this.utilisateurNomComplet = `${etudiant.prenom} ${etudiant.nom}`;

          if (etudiant.photoProfil) {
            // Photo dynamique depuis le backend
            this.photoProfilUrl = `http://localhost:8080/uploads/${etudiant.photoProfil}`;
          }
        } catch (error) {
          console.error('Erreur parsing JSON étudiant :', error);
        }
      }
    }

    toggleNotification(): void {
      this.showNotificationDropdown = !this.showNotificationDropdown;
      this.showUserDropdown = false;
    }

    toggleUserDropdown(): void {
      this.showUserDropdown = !this.showUserDropdown;
      this.showNotificationDropdown = false;
    }

    toggleSidebar(): void {
      this.toggleGauche.emit();
    }

    performSearch(): void {
      console.log('Recherche :', this.searchQuery);
      // Redirection ou filtre dynamique
    }

    @HostListener('document:click', ['$event'])
    handleClickOutside(event: MouseEvent): void {
      const target = event.target as HTMLElement;
      if (!target.closest('.notif-btn') && !target.closest('.user-menu')) {
        this.showNotificationDropdown = false;
        this.showUserDropdown = false;
      }
    }

    logout(): void {
      localStorage.removeItem('token');
      localStorage.removeItem('etudiant');
      window.location.href = '/log';
    }
}
