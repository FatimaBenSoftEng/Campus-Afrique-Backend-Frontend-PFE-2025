import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginRequest {
  email: string;
  password: string;
}

interface RegisterRequest {
  prenom: string;
  nom: string;
  email: string;
  password: string;
}

interface AuthResponse {
  token: string;
}
interface Etudiant {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  photoProfil: string;
  statut: string;
  telephone: string;
  // Ajoute d'autres champs si nécessaires
}

interface AuthResponse {
  token: string;
  etudiant: Etudiant; // 👈 ajouté
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth';

  constructor(private http: HttpClient) {}

  login(data: { email: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data);
  }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data);
  }

}

