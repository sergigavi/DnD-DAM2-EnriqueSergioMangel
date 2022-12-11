import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
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

  cambiarContrasenia(id: string, contra: string)
  {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*')
    headers.append('Accept-Encoding', 'gzip, deflate, br')
    headers.append('Connection', 'keep.alive')

    return this.http.put(`${environment.URLBASE}/usuarios/cambiarContraById/${id}`, contra, {headers:headers, responseType:'json', withCredentials:false});
  }

  getNombreUserById(id:String)
  {
    return this.http.get(`${environment.URLBASE}/usuarios/getNombreById`);
  }
  
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

  actualizarUsuarioById(idUser:string, usuario:IUsuario)//nombre:string, apellidos:string, nickname:string, actividad:boolean, pais:string, fechaNacimiento:Date, biografia:string, urlImage:string)
  {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*')
    headers.append('Accept-Encoding', 'gzip, deflate, br')
    headers.append('Connection', 'keep.alive')

    return this.http.put(`${environment.URLBASE}/usuarios/editarUserById/${idUser}`, usuario, {headers:headers, responseType:'json', withCredentials:false});
  }

  deleteUsuarioById(id:String):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.delete<String>(`${environment.URLBASE}/usuarios/delete/${id}`,requestOptions);
  }

  getCantidad(): Observable<any>{
    return this.http.get<any>(`${environment.URLBASE}/usuarios/cantidad`)
  }

  getUsuarios() : Observable<IUsuario[]>
  {
    return this.http.get<IUsuario[]>(`${environment.URLBASE}/usuarios/dametodos`)
  }

  getUsuario(id:String):Observable<IUsuario>{
    return this.http.get<IUsuario>(`${environment.URLBASE}/usuarios/findByIdString/${id}`);
  }

  existsByEmail(email:String){
    return this.http.get<IUsuario>(`${environment.URLBASE}/usuarios/existsByEmail/${email}`)
  }
}
