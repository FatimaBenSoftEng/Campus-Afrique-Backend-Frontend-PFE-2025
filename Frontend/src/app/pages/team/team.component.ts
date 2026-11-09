import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin
@Component({
  selector: 'app-team',
  standalone: true,
  imports: [RouterModule, FooterComponent, NavbarComponent],
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent {

}
