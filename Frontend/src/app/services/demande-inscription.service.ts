import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DemandeInscription } from '../etudiant/etat-demande/etat-demande.component';

@Injectable({
  providedIn: 'root'
})
export class DemandeInscriptionService {
  private mockData: DemandeInscription[] = [
    {
      id: 1,
      etablissementNom: 'Université de Dakar',
      dateSoumission: '2025-06-20',
      etat: 'VALIDEE'
    },
    {
      id: 2,
      etablissementNom: 'Université Hassan II',
      dateSoumission: '2025-06-25',
      etat: 'EN_ATTENTE'
    },
    {
      id: 3,
      etablissementNom: 'Université Cheikh Anta Diop',
      dateSoumission: '2025-06-28',
      etat: 'REFUSEE'
    },
    {
      id: 4,
      etablissementNom: 'Université Mohammed V',
      dateSoumission: '2025-07-01',
      etat: 'COMPLEMENT',
      remarque: 'Veuillez joindre votre lettre de motivation.'
    }
  ];

  constructor() {}

  // Retourne toutes les demandes de l'étudiant
  getDemandesByEtudiant(etudiantId: number): Observable<DemandeInscription[]> {
    return of(this.mockData); // Simulé
  }

  // Retourne une demande spécifique par ID
  getDemandeByIdEtudiant(idDemande: number, idEtudiant: number): Observable<DemandeInscription | undefined> {
    return this.getDemandesByEtudiant(idEtudiant).pipe(
      map((demandes) => demandes.find(d => d.id === idDemande))
    );
  }

}
