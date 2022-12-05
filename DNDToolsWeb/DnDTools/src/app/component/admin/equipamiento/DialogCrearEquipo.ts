import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { EquipamientoComponent } from "./equipamiento.component";
import {  NgForm } from '@angular/forms';
import { EquipamientoAdminService } from "src/app/services/equipamientoAdmin-service/equipamiento-admin.service";

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
  ARMADURA="ARMADURA",
  ARMA="ARMA",
  EQUIPO_DE_AVENTURAS="EQUIPO_DE_AVENTURAS"
}
export enum CatEquipo{
  ARMADURA_INTERMEDIA="ARMADURA_INTERMEDIA",
  ARMADURA_LIGERA="ARMADURA_LIGERA",
  ARMADURA_PESADA="ARMADURA_PESADA",
	ARMA_MARCIAL="ARMA_MARCIAL",
  ARMA_SENCILLA="ARMA_SENCILLA",
  ESCUDO="ESCUDO",
  HERRAMIENTA="HERRAMIENTA",
  INSTRUMENTO_MUSICAL="INSTRUMENTO_MUSICAL",
	JUEGO="JUEGO",
  KIT="KIT",
  MONTURA="MONTURA",
  MUNICION="MUNICION",
  OTROS="OTROS",
  PAQUETE_DE_EQUIPO="PAQUETE_DE_EQUIPO",
  VEHICULO="VEHICULO"
}

export enum PropiedadEquipo{
  ALCANCE="ALCANCE",
  ARROJADIZO="ARROJADIZO",
  CARGADOR="CARGADOR",
  DE_CARGA="DE_CARGA",
  DESVENTAJA="DESVENTAJA",
  DISTANCIA="DISTANCIA",
  DOS_MANOS="DOS_MANOS",
  ESPECIAL="ESPECIAL",
  FOCO_ARCANO="FOCO_ARCANO",
  FOCO_DUIDRICO="FOCO_DUIDRICO",
	FUERZA="FUERZA",
  LIGERO="LIGERO",
  MUNICION="MUNICION",
  MUNICION_ESPECIAL="MUNICION_ESPECIAL",
  PESADO="PESADO",
  RAFAGA="RAFAGA",
  SIMBOLO_SAGRADO="SIMBOLO_SAGRADO",
  SINTONIZADO="SINTONIZADO",
  SUTIL="SUTIL",
  VERSATIL="VERSATIL"
}

export enum Modificador{
  FUE="FUE",
  DES="DES",
  INT="INT",
  SAB="SAB",
  CAR="CAR"
}

@Component({
  selector: 'dialogCrearEquipo',
  templateUrl: './dialogCrearEquipo.html',
  styleUrls: ['./dialogCrearEquipo.css']
})
export class DialogCrearEquipo{
  constructor(public dialogRef:MatDialogRef<EquipamientoComponent>,private equipamientoServicio:EquipamientoAdminService){}

  tipos=Object.values(Tipo)
  categorias=Object.values(CatEquipo)
  propiedades=Object.values(PropiedadEquipo)
  modificadores=Object.values(Modificador)

  equipamiento!: EquipamientoAdmin;

  tipoElegido:string="";
  categoriaElegida:string="";
  propiedadElegida:string[]=[]
  modificadorElegido:string="";

  onNoClick(){
    this.dialogRef.close()
  }

  ngOnInit(){

  }

  onSubmit(equipo:NgForm){
    this.equipamiento=equipo.value
    if(this.equipamiento.propiedad.length<1){
      this.equipamiento.propiedad=[]
    }
    this.equipamientoServicio.addEquipo(equipo.value).subscribe((data:any)=>{
      console.log(data)
      this.dialogRef.close()
    })
  }
}
