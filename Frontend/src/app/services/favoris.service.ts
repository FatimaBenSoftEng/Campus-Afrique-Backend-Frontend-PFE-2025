// favoris.service.ts
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

export interface Favori {
  idEtablissement: number;
  nom: string;
  ville: string;
  pays: string;
  imageUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class FavorisService {
  private favoris: Favori[] = [];

  getFavoris(): Observable<Favori[]> {
    return of(this.favoris.slice(0, 5));
  }

  ajouterFavori(etab: Favori): void {
    if (this.favoris.length < 5 && !this.favoris.find(f => f.idEtablissement === etab.idEtablissement)) {
      this.favoris.push(etab);
    }
  }

  retirerFavori(id: number): void {
    this.favoris = this.favoris.filter(f => f.idEtablissement !== id);
  }
}
