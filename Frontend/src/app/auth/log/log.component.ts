import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-log',
  standalone: true,
  imports: [RouterLink, FormsModule,FooterComponent, NavbarComponent],
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent {
  form = {
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
   this.authService.login(this.form).subscribe({
     next: (res) => {
       localStorage.setItem('token', res.token);
        localStorage.setItem('etudiant', JSON.stringify(res.etudiant));
       this.router.navigate(['/tableau-de-bord']);
     },
      error: (err) => {
        console.error("Erreur de connexion", err);
        alert("Identifiants invalides.");
      }
    });
  }


}
