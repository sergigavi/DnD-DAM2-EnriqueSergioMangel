import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IFichaPersonaje } from 'src/modelo/IFichaPersonaje';

@Injectable({
  providedIn: 'root'
})
export class FichaAdminService {

  constructor(private http:HttpClient) { }

  getAll():Observable<IFichaPersonaje[]>{
    return this.http.get<IFichaPersonaje[]>(`${environment.URLBASE}/personajes/getAll`);
  }
}
