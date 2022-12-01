import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

export interface Usuario {

  idUser?:String;
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


  constructor(private http: HttpClient)
  {

  }

  annadirUsuario(usuario: Usuario): Observable<any>
  {
    return this.http.post(`${environment.URLBASE}/usuarios/insertarUsuario`, usuario)
  };

  eliminarUsuario(usuario: Usuario)
  {

  };

  getUsuarios() : Observable<Usuario[]>
  {
    return this.http.get<Usuario[]>(`${environment.URLBASE}/usuarios/dametodos`)
  }
}
