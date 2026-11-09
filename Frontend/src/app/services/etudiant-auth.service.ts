import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

export interface Etudiant {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  paysOrigine: string;
  dateNaissance: string;
  nationalite: string;
  photoProfil?: string;
  statut: string;
  telephone: string;
  adresse: string;
  ville: string;
  niveauScolaire?: {
    id: number;
    nom: string;
  };
}

export interface Notification {
  id: number;
  message: string;
  time: string;
  read: boolean;
  type: 'info' | 'success' | 'warning' | 'error';
}

@Injectable({
  providedIn: 'root'
})
export class EtudiantAuthService {
  private apiUrl = '/api';
  private currentEtudiantSubject = new BehaviorSubject<Etudiant | null>(null);
  private notificationsSubject = new BehaviorSubject<Notification[]>([]);

  public currentEtudiant$ = this.currentEtudiantSubject.asObservable();
  public notifications$ = this.notificationsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadCurrentEtudiant();
    this.loadNotifications();
  }

  // Charger les données de l'étudiant connecté
  private loadCurrentEtudiant(): void {
    const token = localStorage.getItem('token');
    if (token) {
      // Décoder le token JWT pour obtenir l'ID de l'étudiant
      const payload = this.decodeToken(token);
      if (payload && payload.sub) {
        this.getEtudiantById(payload.sub).subscribe({
          next: (etudiant) => {
            this.currentEtudiantSubject.next(etudiant);
          },
          error: (error) => {
            console.error('Erreur lors du chargement de l\'étudiant:', error);
            this.logout();
          }
        });
      }
    }
  }

  // Décoder le token JWT
  private decodeToken(token: string): any {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      return JSON.parse(jsonPayload);
    } catch (error) {
      console.error('Erreur lors du décodage du token:', error);
      return null;
    }
  }

  // Obtenir un étudiant par ID
  getEtudiantById(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.apiUrl}/etudiants/find/${id}`).pipe(
      catchError(error => {
        console.error('Erreur lors de la récupération de l\'étudiant:', error);
        throw error;
      })
    );
  }

  // Charger les notifications
  private loadNotifications(): void {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = this.decodeToken(token);
      if (payload && payload.sub) {
        this.getNotificationsByEtudiantId(payload.sub).subscribe({
          next: (notifications) => {
            this.notificationsSubject.next(notifications || []);
          },
          error: (error) => {
            console.error('Erreur lors du chargement des notifications:', error);
            this.notificationsSubject.next([]);
          }
        });
      }
    }
  }

  // Obtenir les notifications d'un étudiant
  getNotificationsByEtudiantId(etudiantId: number): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/notifications/etudiant/${etudiantId}`).pipe(
      catchError(error => {
        console.error('Erreur lors de la récupération des notifications:', error);
        return of([]);
      })
    );
  }

  // Marquer une notification comme lue
  markNotificationAsRead(notificationId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/notifications/${notificationId}/read`, {}).pipe(
      tap(() => {
        const currentNotifications = this.notificationsSubject.value;
        const updatedNotifications = currentNotifications.map(notif =>
          notif.id === notificationId ? { ...notif, read: true } : notif
        );
        this.notificationsSubject.next(updatedNotifications);
      }),
      catchError(error => {
        console.error('Erreur lors du marquage de la notification:', error);
        throw error;
      })
    );
  }

  // Marquer toutes les notifications comme lues
  markAllNotificationsAsRead(): Observable<any> {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = this.decodeToken(token);
      if (payload && payload.sub) {
        return this.http.put(`${this.apiUrl}/notifications/etudiant/${payload.sub}/read-all`, {}).pipe(
          tap(() => {
            const currentNotifications = this.notificationsSubject.value;
            const updatedNotifications = currentNotifications.map(notif => ({ ...notif, read: true }));
            this.notificationsSubject.next(updatedNotifications);
          }),
          catchError(error => {
            console.error('Erreur lors du marquage des notifications:', error);
            throw error;
          })
        );
      }
    }
    return of(null);
  }

  // Obtenir le nombre de notifications non lues
  getUnreadNotificationCount(): number {
    const notifications = this.notificationsSubject.value;
    return notifications.filter(n => !n.read).length;
  }

  // Obtenir le nom complet de l'étudiant
  getFullName(): string {
    const etudiant = this.currentEtudiantSubject.value;
    if (etudiant && etudiant.prenom && etudiant.nom) {
      return `${etudiant.prenom} ${etudiant.nom}`;
    }
    return 'Étudiant';
  }

  // Obtenir la photo de profil
  getProfilePhoto(): string {
    const etudiant = this.currentEtudiantSubject.value;
    if (etudiant && etudiant.photoProfil) {
      return etudiant.photoProfil;
    }
    return 'assets/img/default-avatar.jpg';
  }

  // Vérifier si l'utilisateur est connecté
  isLoggedIn(): boolean {
    return this.currentEtudiantSubject.value !== null;
  }

  // Déconnexion
  logout(): void {
    localStorage.removeItem('token');
    this.currentEtudiantSubject.next(null);
    this.notificationsSubject.next([]);
  }

  // Rafraîchir les données
  refreshData(): void {
    this.loadCurrentEtudiant();
    this.loadNotifications();
  }
}
