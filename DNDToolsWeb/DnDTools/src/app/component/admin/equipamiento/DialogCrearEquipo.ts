import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { EquipamientoComponent } from "./equipamiento.component";
import { NgForm } from '@angular/forms';

export enum Tipo{
  ARMADURA, ARMA, EQUIPO_DE_AVENTURAS
}


@Component({
  selector: 'dialogCrearEquipo',
  templateUrl: 'dialogCrearEquipo.html',
})
export class DialogCrearEquipo{
  constructor(public dialogRef:MatDialogRef<EquipamientoComponent>){}

  tipos:String[]=["ARMADURA", "ARMA", "EQUIPO_DE_AVENTURAS"]

  tipoElegido:string="";

  onNoClick(){
    this.dialogRef.close()
  }

  onSubmit(equipo:NgForm){
    alert("Enviado")
    this.dialogRef.close()
  }
}
