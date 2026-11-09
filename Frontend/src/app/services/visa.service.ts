import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VisaService {
  private apiUrl = 'http://localhost:8080/api/visas';

  constructor(private http: HttpClient) {}

  envoyerDemande(formData: FormData): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.post(`${this.apiUrl}/formulaire`, formData, { headers });
  }

  getMesDemandes(): Observable<any[]> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<any[]>('http://localhost:8080/api/visas/mes-demandes', { headers });
  }


}
