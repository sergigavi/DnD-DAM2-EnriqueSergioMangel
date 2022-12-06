import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

export interface Usuario {

  idUser?:any;
  nombre?:String;
  apellidos?:String;
  contrasenia:String;
  nickname:String;
  biografia?:String;
  email?:String;
  fechaNacimiento?:String;
  urlImage?:String;
  activo?:boolean;
  pais?:String;

}


@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {
  [x: string]: any;


  constructor(private http: HttpClient)
  {

  }

  annadirUsuario(usuario: Usuario): Observable<any>
  {
    return this.http.post(`${environment.URLBASE}/usuarios/insertarUsuario`,usuario)
  };

  eliminarUsuario(usuario: Usuario)
  {

  };

  updateUsuario(usuario:Usuario): Observable<any>
  {
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.put<any>(`${environment.URLBASE}/usuarios/update`,usuario,requestOptions)
  }

  getUsuarios() : Observable<Usuario[]>
  {
    return this.http.get<Usuario[]>(`${environment.URLBASE}/usuarios/dametodos`)
  }
}
