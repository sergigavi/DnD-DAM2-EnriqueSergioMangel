import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IPartida } from 'src/modelo/IPartida';

@Injectable({
  providedIn: 'root'
})
export class PartidaServiceService {

  constructor(private http:HttpClient) { }

  getAll():Observable<IPartida[]>{
    return this.http.get<IPartida[]>(`${environment.URLBASE}/partida/getAll`);
  }
}
