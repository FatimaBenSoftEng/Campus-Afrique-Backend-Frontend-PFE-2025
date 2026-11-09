import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

// 🔽 Interface ici
export interface DashboardStats {
  totalCandidatures: number;
  enAttente: number;
  totalBourses: number;
  repartitionStatuts: { [key: string]: number };
  demandesParType: { [key: string]: number };
  statutsParType: { [key: string]: { [key: string]: number } };
  datesImportantes: string[]; // Format: "30/09/2025 - Fin des candidatures"
  documentsRecents: string[]; // Format: "Diplôme bac (1)"
}
@Injectable({ providedIn: 'root' })
export class DashboardService {
  private apiUrl = 'http://localhost:8080/api/dashboard';

  constructor(private http: HttpClient) {}

  getDashboardStats(): Observable<DashboardStats> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<DashboardStats>(this.apiUrl, { headers });
  }
}

