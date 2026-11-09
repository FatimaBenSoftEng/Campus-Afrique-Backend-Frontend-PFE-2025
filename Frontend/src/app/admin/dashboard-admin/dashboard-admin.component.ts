import { Component } from '@angular/core';
import { SlidebarAdminFixComponent } from '../../shared/slidebar-admin-fix/slidebar-admin-fix.component';
import { NavbarAdminComponent } from '../../shared/navbar-admin/navbar-admin.component';
import { FooterAdminComponent } from '../../shared/footer-admin/footer-admin.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';
import { CommonModule} from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard-admin',
  standalone: true,
  imports: [CommonModule, RouterModule,
            SlidebarAdminFixComponent, NavbarAdminComponent,
            FooterAdminComponent, SidebarEvenementsComponent],
  templateUrl: './dashboard-admin.component.html',
  styleUrl: './dashboard-admin.component.scss'
})
export class DashboardAdminComponent {
   sidebarVisible = true;
   toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }

}
