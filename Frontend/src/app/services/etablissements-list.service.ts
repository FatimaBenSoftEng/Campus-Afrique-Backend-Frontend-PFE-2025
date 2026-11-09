import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

export interface Formation {
  id?: number;
  nom: string;
  description?: string;
  duree?: number;
  fraisAnnuel?: number;
}

export interface EtablissementListItem {
  id: number;
  nom: string;
  adresse?: string;
  description?: string;
  email?: string;
  siteWeb?: string;
  bourseDisponible?: boolean;
  ville?: string;
  pays?: string;
  nomAbr?: string;
  type?: string;
  imageUrl?: string;
  isFavori?: boolean;

  // 👉 Ajout de cette ligne :
  formations?: Formation[];
}


@Injectable({
  providedIn: 'root'
})
export class EtablissementsListService {
  private readonly FAVORIS_KEY = 'campus_favoris_ids';

  // 👇 Remplacer par ton URL backend directement ici
  private apiUrl = 'http://localhost:8080/api/etablissements';

  constructor(private http: HttpClient) {}

  getEtablissements(): Observable<EtablissementListItem[]> {
    const favorisIds = this.getFavorisIdsFromStorage();
    return this.http.get<EtablissementListItem[]>(`${this.apiUrl}/all`).pipe(
      map(etabs =>
        etabs.map(e => ({
          ...e,
          isFavori: favorisIds.includes(e.id)
        }))
      )
    );
  }

  getFavoris(): Observable<EtablissementListItem[]> {
    return this.getEtablissements().pipe(
      map(etabs => etabs.filter(e => e.isFavori))
    );
  }

  toggleFavori(id: number): void {
    const favorisIds = this.getFavorisIdsFromStorage();
    const index = favorisIds.indexOf(id);

    if (index > -1) {
      favorisIds.splice(index, 1); // Retirer
    } else {
      if (favorisIds.length >= 5) {
        alert('Vous avez déjà 5 établissements dans vos favoris.');
        return;
      }
      favorisIds.push(id); // Ajouter
    }

    localStorage.setItem(this.FAVORIS_KEY, JSON.stringify(favorisIds));
  }

  private getFavorisIdsFromStorage(): number[] {
    const saved = localStorage.getItem(this.FAVORIS_KEY);
    return saved ? JSON.parse(saved) : [];
  }
}
