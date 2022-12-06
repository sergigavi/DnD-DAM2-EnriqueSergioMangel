import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface EquipamientoAdmin{
  nombre:String;
  tipo:Tipo;
  categoria:CatEquipo;
  propiedad:PropiedadEquipo[];
  modificador:String;
  danio:String;
  alcance:number;
  precio:String;
  peso:String;
  descripcion:String;
}

export enum Tipo{
  ARMADURA, ARMA, EQUIPO_DE_AVENTURAS
}

export enum CatEquipo{
  ARMADURA_INTERMEDIA, ARMADURA_LIGERA, ARMADURA_PESADA,
	ARMA_MARCIAL, ARMA_SENCILLA, ESCUDO, HERRAMIENTA, INSTRUMENTO_MUSICAL,
	JUEGO, KIT, MONTURA, MUNICION, OTROS, PAQUETE_DE_EQUIPO, VEHICULO
}

export enum PropiedadEquipo{
  ALCANCE, ARROJADIZO, CARGADOR, DE_CARGA, DESVENTAJA, DISTANCIA, DOS_MANOS, ESPECIAL, FOCO_ARCANO, FOCO_DUIDRICO,
	FUERZA, LIGERO, MUNICION, MUNICION_ESPECIAL, PESADO, RAFAGA, SIMBOLO_SAGRADO, SINTONIZADO, SUTIL, VERSATIL
}


@Injectable({
  providedIn: 'root'
})
export class EquipamientoAdminService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<EquipamientoAdmin[]>{
    return this.http.get<EquipamientoAdmin[]>(`${environment.URLBASE}/equipamiento/getAll`)
  }

  addEquipo(equipo:EquipamientoAdmin):Observable<String>{
    const requestOptions:Object = {
      responseType:'text'
    }
    return this.http.post<String>(`${environment.URLBASE}/equipamiento/add`,equipo,requestOptions)
  }

  updateEquipo(equipo:EquipamientoAdmin):Observable<any>{
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
