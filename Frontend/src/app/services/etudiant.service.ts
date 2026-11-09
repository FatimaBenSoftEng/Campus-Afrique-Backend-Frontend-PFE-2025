import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class EtudiantService {
  private apiUrl = 'http://localhost:8080/api/etudiants';

  constructor(private http: HttpClient) {}

  getProfil(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me`);
  }

 /*   updateProfil(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, data);
  }
 */
   updateProfilFormData(id: number, formData: FormData): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.put(`${this.apiUrl}/update/${id}`, formData, { headers });
  }
/* updateProfilFormData(id: number, formData: FormData): Observable<any> {
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.put(`${this.apiUrl}/update/${id}`, formData, { headers });
} */


}
