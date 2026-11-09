import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface DocumentDTO {
  id: number;
  nom: string;
  type: string;
  cheminFichier: string;
  dateAjout: string;
  statut: string;
}

@Injectable({ providedIn: 'root' })
export class DocumentService {
  private apiUrl = 'http://localhost:8080/api/documentsConsultation/mes-documents';

  constructor(private http: HttpClient) {}

  getDocuments(): Observable<DocumentDTO[]> {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<DocumentDTO[]>(this.apiUrl, { headers });
  }
}
