// hebergement.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class HebergementService {
  private baseUrl = 'http://localhost:8080/api/hebergements';

  constructor(private http: HttpClient) {}

  envoyerDemande(formData: FormData): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token
    });

    return this.http.post(`${this.baseUrl}/formulaire`, formData, { headers });
  }

  getDemandes(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token
    });

    return this.http.get<any[]>(`${this.baseUrl}/mes`, { headers });
  }
}
