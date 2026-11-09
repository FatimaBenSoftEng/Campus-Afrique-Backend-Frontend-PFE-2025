// etablissement-detail.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Formation {
  id: number;
  nom: string;
  description: string;
  duree: number;
  fraisAnnuel: number;
}

export interface EtablissementDetail {
  id: number;
  nom: string;
  nomAbr: string;
  description: string;
  imageUrl?: string;
  adresse: string;
  ville: string;
  pays: string;
  telephone: string;
  email: string;
  siteWeb: string;
  type: string;
  dateDebutCandidature?: string;
  dateFinCandidature?: string;
  dateCreation?: string;
  typeDiplome?: string;
  reconnaissanceEtat?: string;
  bourseDisponible: boolean;
  parcours: string[];               // ✅ Parcours d’études
  formations: Formation[];         // ✅ Formations proposées
  fraisScolariteMin?: number;      // ✅ Min
  fraisScolariteMax?: number;      // ✅ Max
}

@Injectable({
  providedIn: 'root'
})
export class EtablissementDetailService {
  private apiUrl = 'http://localhost:8080/api/etablissements';

  constructor(private http: HttpClient) {}

  getEtablissementById(id: number): Observable<EtablissementDetail> {
    return this.http.get<EtablissementDetail>(`${this.apiUrl}/find/${id}`);
  }
}
