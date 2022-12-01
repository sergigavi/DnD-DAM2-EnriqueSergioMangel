import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface Jugador{
    idJugador:String;
    idUsuario:String;
    idFicha:String;
    notas:String;
}


@Injectable({
  providedIn: 'root'
})

export class JugadorService {

  constructor(private http:HttpClient) { }

  getAll() :Observable<Jugador[]>{
    return this.http.get<Jugador[]>(`${environment.URLBASE}/jugador/getAll`)
  }
}
