import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EtatDemandeService {
  private apiUrl = 'http://localhost:8080/api/etat-demandes'; // adapte si besoin

  constructor(private http: HttpClient) {}

  getEtatDemandes(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
