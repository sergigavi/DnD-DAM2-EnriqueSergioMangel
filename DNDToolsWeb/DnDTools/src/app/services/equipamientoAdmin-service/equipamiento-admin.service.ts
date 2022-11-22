import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface EquipamientoAdmin{
  nombre:String;
  tipo:String;
  categoria:String;
  propiedad:String;
  modificador:String;
  danio:String;
  alcance:String;
  precio:String;
  peso:String;
  descripcion:String;
}


@Injectable({
  providedIn: 'root'
})
export class EquipamientoAdminService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<EquipamientoAdmin[]>{
    return this.http.get<EquipamientoAdmin[]>(`${environment.URLBASE}/equipamiento`)
  }
}
