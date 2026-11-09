import { Component } from '@angular/core';
import { SlidebarEtablissementFixComponent } from '../../shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtablissementComponent } from '../../shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent } from '../../shared/footer-etablissement/footer-etablissement.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule} from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-preregistration-list',
  standalone: true,
  imports: [CommonModule, RouterModule,
            SlidebarEtablissementFixComponent, NavbarEtablissementComponent,
            FooterEtablissementComponent, SidebarEvenementsComponent],
  templateUrl: './preregistration-list.component.html',
  styleUrl: './preregistration-list.component.scss'
})
export class PreregistrationListComponent {
   sidebarVisible = true;
    toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }

}
