import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface Caracteristica{
  nombre:String;
  nombreIniciales:String;
  valorTotal:String;
  valorMod:String;
}


@Injectable({
  providedIn: 'root'
})

export class CaracteristicaService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<Caracteristica[]>{
    return this.http.get<Caracteristica[]>(`${environment.URLBASE}/caracteristicas/getAll`)
  }
}
