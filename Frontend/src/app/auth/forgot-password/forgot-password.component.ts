import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../shared/navbar/navbar.component'; // Ajustez le chemin
import { FooterComponent } from '../../shared/footer/footer.component'; // Ajustez le chemin

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, CommonModule, FooterComponent, NavbarComponent],
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent {
  resetForm;

  isSubmitted = false;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    // Initialisation de resetForm dans le constructeur
    this.resetForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit() {
    if (this.resetForm.invalid) return;

    this.isLoading = true;

    // Simuler l'envoi à l'API
    setTimeout(() => {
      this.isLoading = false;
      this.isSubmitted = true;
    }, 1500);
  }

  goToLogin() {
    this.router.navigate(['/log']);
  }
}
