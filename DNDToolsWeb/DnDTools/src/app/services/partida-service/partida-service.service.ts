import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IPartida } from 'src/modelo/IPartida';
import { IUsuario } from 'src/modelo/IUsuario';

@Injectable({
  providedIn: 'root'
})
export class PartidaServiceService {

  constructor(private http:HttpClient) { }

  getAll():Observable<IPartida[]>{
    return this.http.get<IPartida[]>(`${environment.URLBASE}/partida/getAll`);
  }

  addPartida(usuario:IUsuario,partida:IPartida):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }

    return this.http.post<String>(`${environment.URLBASE}/partida/addPartida/${usuario}/${partida}`, requestOptions)
  }
}
