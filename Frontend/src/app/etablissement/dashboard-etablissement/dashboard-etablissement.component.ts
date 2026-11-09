import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule} from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard-etablissement',
  standalone: true,

  imports: [CommonModule, RouterModule,
            SlidebarEtablissementFixComponent, NavbarEtablissementComponent,
            FooterEtablissementComponent, SidebarEvenementsComponent],
  templateUrl: './dashboard-etablissement.component.html',
  styleUrl: './dashboard-etablissement.component.scss'
})
export class DashboardEtablissementComponent {

  sidebarVisible = true;
  toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }
  goToStudents() { /* router.navigate vers la liste des étudiants */ }
  goToCourses() { /* router.navigate vers les cours */ }
  goToTeachers() { /* router.navigate vers les enseignants */ }
  goToEvents() { /* router.navigate vers les événements */ }

}
