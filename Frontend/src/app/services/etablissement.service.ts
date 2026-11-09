import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Etablissement {
  id: number;
  nom: string;
  description: string;
  ville: string;
  type: string;
  imageUrl: string;
  email: string;
  siteWeb: string;
  pays: string;
  nomAbr: string;
  bourseDisponible: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class EtablissementService {

  private apiBase = '/api/etablissements';  // ✅ relative path → proxy works!

  constructor(private http: HttpClient) {}

  getEtablissements(): Observable<Etablissement[]> {
    return this.http.get<Etablissement[]>(`${this.apiBase}/all`);
  }

  searchEtablissements(keyword: string): Observable<Etablissement[]> {
    return this.http.get<Etablissement[]>(`${this.apiBase}/search?keyword=${encodeURIComponent(keyword)}`);
  }

getByType(type: string): Observable<Etablissement[]> {
  return this.http.get<Etablissement[]>(`${this.apiBase}/find/type/${encodeURIComponent(type)}`);
}

}
