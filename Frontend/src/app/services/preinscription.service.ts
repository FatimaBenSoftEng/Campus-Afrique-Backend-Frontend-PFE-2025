import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreinscriptionService {
  private apiUrl = 'http://localhost:8080/api/preinscriptions';

  constructor(private http: HttpClient) {}

  envoyerFormulaire(formData: FormData): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post(`${this.apiUrl}/formulaire`, formData, { headers });
  }

  getMesPreinscriptions(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any[]>(`${this.apiUrl}/mes-demandes`, { headers });
  }

  supprimerPreinscription(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getPreinscriptionById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  updatePreinscription(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, data);
  }

  getStatutsDisponibles(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/statuts`);
  }
}
