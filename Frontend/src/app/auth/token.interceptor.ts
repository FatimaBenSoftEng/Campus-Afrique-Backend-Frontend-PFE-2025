import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    const isAuthRequest = req.url.includes('/auth/register') || req.url.includes('/auth/authenticate');

    let authReq = req;
    if (token && !isAuthRequest) {
      authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
    }

    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 || error.status === 403) {
          // Token expiré ou invalide
          localStorage.removeItem('token');
          localStorage.removeItem('etudiant');
          alert('Votre session a expiré. Veuillez vous reconnecter.');
          this.router.navigate(['/log']);
        }
        return throwError(() => error);
      })
    );
  }
}
