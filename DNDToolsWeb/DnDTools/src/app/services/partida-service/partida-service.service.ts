import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  addPartida(partida:IPartida):Observable<String>{

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*')
    headers.append('Accept-Encoding', 'gzip, deflate, br')
    headers.append('Connection', 'keep.alive')

    console.log(partida)
    return this.http.post<String>(`${environment.URLBASE}/partida/add`, partida, {headers:headers, responseType:'json', withCredentials:false})
  }
}
