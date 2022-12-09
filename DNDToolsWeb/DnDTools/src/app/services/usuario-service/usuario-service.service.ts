import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { IUsuario } from 'src/modelo/IUsuario';


@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {

  constructor(private http: HttpClient)
  {

  }

  getAll() :Observable<IUsuario[]>{
    return this.http.get<IUsuario[]>(`${environment.URLBASE}/usuarios/getAll`)
  }

  annadirUsuario(usuario: IUsuario): Observable<any>
  {
    return this.http.post(`${environment.URLBASE}/usuarios/insertarUsuario`,usuario)
  };

  eliminarUsuario(usuario: IUsuario)
  {

  };

  addUsuario(usuario:IUsuario):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.post<String>(`${environment.URLBASE}/usuarios/add`,usuario,requestOptions)
  }

  updateUsuario(usuario:IUsuario): Observable<any>
  {
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.put<any>(`${environment.URLBASE}/usuarios/update`,usuario,requestOptions)
  }

  deleteUsuarioById(id:String):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.delete<String>(`${environment.URLBASE}/usuarios/delete/${id}`,requestOptions);
  }

  getUsuarios() : Observable<IUsuario[]>
  {
    return this.http.get<IUsuario[]>(`${environment.URLBASE}/usuarios/dametodos`)
  }

  getUsuario(id:String):Observable<IUsuario>{
    return this.http.get<IUsuario>(`${environment.URLBASE}/usuarios/findById/${id}`);
  }

  existsByEmail(email:String){
    return this.http.get<IUsuario>(`${environment.URLBASE}/usuarios/existsByEmail/${email}`)
  }
}
