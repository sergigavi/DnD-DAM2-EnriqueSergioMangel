import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IFichaPersonaje } from 'src/modelo/IFichaPersonaje';

@Injectable({
  providedIn: 'root'
})
export class FichaAdminService {

  constructor(private http:HttpClient) { }

  getAll():Observable<IFichaPersonaje[]>{
    return this.http.get<IFichaPersonaje[]>(`${environment.URLBASE}/personajes/getAll`);
  }

  addFicha(ficha:IFichaPersonaje):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.post<String>(`${environment.URLBASE}/personajes/addFicha`,ficha,requestOptions);
  }

  addFichaVacia():Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.post<String>(`${environment.URLBASE}/personajes/addFichaVacia`,null,requestOptions);
  }

  updateFicha(ficha:IFichaPersonaje):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.put<String>(`${environment.URLBASE}/personajes/update`,ficha,requestOptions);
  }

  deleteFicha(id:String):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.delete<String>(`${environment.URLBASE}/personajes/deleteById/${id}`,requestOptions);
  }

  deleteAllFicha():Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.delete<String>(`${environment.URLBASE}/personajes/deleteAll`,requestOptions)
  }

  getCantidad(): Observable<any>{
    return this.http.get<any>(`${environment.URLBASE}/personajes/cantidad`)
  }
}
