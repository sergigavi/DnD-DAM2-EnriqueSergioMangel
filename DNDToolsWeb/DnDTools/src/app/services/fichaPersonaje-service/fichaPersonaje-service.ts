import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


export interface FichaPersonaje{
  inventario:String;
	caracteristicas:String;
	habilidades:String;
	clase:String;
	raza:String;
	alineamiento:String;
	nivel:String;
	bonifCompetencia:String;
  transfondo:String;
  ca:String;
  velocidad:String;
	puntosVidaMax:String;
	puntosVidaAct:String;
	rasgosPersonalidad:String;
	ideales:String;
	vinculos:String;
	defectos:String;
	rasgosAtt:String;
	otrasComp:String;
	apariencia:String;
	historiaPersonal:String;
	rasgos:String;
	notasAdd:String;
}


@Injectable({
  providedIn: 'root'
})

export class FichaPersonajeService {

  constructor(private http:HttpClient) { }


  getAll() :Observable<FichaPersonaje[]>{
    return this.http.get<FichaPersonaje[]>(`${environment.URLBASE}/fichaPersonaje/getAll`)
  }
}

