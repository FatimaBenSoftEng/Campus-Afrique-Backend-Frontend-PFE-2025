import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class TransportService {
  private apiUrl = 'http://localhost:8080/api/transports';

  constructor(private http: HttpClient) {}

  envoyerDemande(formData: FormData): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.post(`${this.apiUrl}/formulaire`, formData, { headers });
  }

  getDemandes(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token
    });

    // 🔄 Correction ici : remplacer baseUrl par apiUrl
    return this.http.get<any[]>(`${this.apiUrl}/mes`, { headers });
  }
}
