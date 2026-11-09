import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-sign',
  standalone: true,
  imports: [RouterLink, FormsModule,FooterComponent, NavbarComponent],
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss']
})
export class SignComponent {
  form = {
    nom: '',
    prenom: '',
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.register(this.form).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/log']);
      },
      error: (err) => {
        console.error("Erreur d'inscription", err);
        alert("Échec de l'inscription.");
      }
    });
  }
}
