import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule} from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-application-validation',
  standalone: true,
  imports: [CommonModule, RouterModule,
            SlidebarEtablissementFixComponent, NavbarEtablissementComponent,
            FooterEtablissementComponent, SidebarEvenementsComponent],
  templateUrl: './application-validation.component.html',
  styleUrl: './application-validation.component.scss'
})
export class ApplicationValidationComponent {
   sidebarVisible = true;
    toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }

}
