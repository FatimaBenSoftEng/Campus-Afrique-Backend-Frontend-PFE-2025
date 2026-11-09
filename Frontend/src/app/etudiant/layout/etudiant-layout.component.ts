import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';

@Component({
  selector: 'app-etudiant-layout',
  standalone: true,
  imports: [
    RouterModule,
    FooterEtudiantComponent,
    NavbarEtudiantComponent,
    SidebarEtudiantFixComponent
  ],
  templateUrl: './etudiant-layout.component.html',
  styleUrls: ['./etudiant-layout.component.scss']
})
export class EtudiantLayoutComponent {
  sidebarVisible = true;

  toggleSidebar() {
    this.sidebarVisible = !this.sidebarVisible;
  }
}
