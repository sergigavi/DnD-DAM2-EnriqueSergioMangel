import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IEquipo } from 'src/modelo/IEquipo';


@Injectable({
  providedIn: 'root'
})
export class EquipamientoAdminService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<IEquipo[]>{
    return this.http.get<IEquipo[]>(`${environment.URLBASE}/equipamiento/getAll`)
  }

  addEquipo(equipo:IEquipo):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.post<String>(`${environment.URLBASE}/equipamiento/add`,equipo,requestOptions)
  }

  updateEquipo(equipo:IEquipo):Observable<any>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.put<any>(`${environment.URLBASE}/equipamiento/update`,equipo,requestOptions);
  }

  deleteEquipoById(id:String):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.delete<String>(`${environment.URLBASE}/equipamiento/delete/${id}`,requestOptions);
  }

  deleteAllEquipo():Observable<boolean>{
    return this.http.delete<any>(`${environment.URLBASE}/equipamiento/deleteAll`)
  }
}
