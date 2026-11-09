import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-feature',
  standalone: true,
  imports: [RouterModule, FooterComponent, NavbarComponent],
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.scss']
})
export class FeatureComponent { }
