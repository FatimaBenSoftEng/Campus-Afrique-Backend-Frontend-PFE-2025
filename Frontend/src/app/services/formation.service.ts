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

@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private apiUrl = 'http://localhost:8080/api/formations';

  constructor(private http: HttpClient) {}

  getFormationsByEtablissement(etabId: number): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${this.apiUrl}/etablissement/${etabId}`);
  }
}
