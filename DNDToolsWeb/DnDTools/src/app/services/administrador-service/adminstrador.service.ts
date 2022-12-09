import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface Administrador{
   idAdmin:String;
	 nombre:String;
	 email:String;
   contrasenia:String;
}


@Injectable({
  providedIn: 'root'
})
export class AdministradorService {

  constructor(private http:HttpClient) { }

  getAll() :Observable<Administrador[]>{
    return this.http.get<Administrador[]>(`${environment.URLBASE}/admins/getAll`)
  }
  existsById(id:String):Observable<boolean>{
    return this.http.get<boolean>(`${environment.URLBASE}/admins/existsById/${id}`)
  }
  existsByEmail(email:String){
    return this.http.get<boolean>(`${environment.URLBASE}/admins/existsByEmail/${email}`)
  }
}
