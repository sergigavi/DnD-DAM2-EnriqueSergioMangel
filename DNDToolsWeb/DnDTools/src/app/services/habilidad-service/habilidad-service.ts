import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface Habilidad{
  nombre:String;
	competencia:String;
  mod:String;
}


@Injectable({
  providedIn: 'root'
})

export class HabilidadService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<Habilidad[]>{
    return this.http.get<Habilidad[]>(`${environment.URLBASE}/habilidad/getAll`)
  }
}
