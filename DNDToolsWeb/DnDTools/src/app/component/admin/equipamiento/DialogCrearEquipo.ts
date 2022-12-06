import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { EquipamientoComponent } from "./equipamiento.component";
import {  NgForm } from '@angular/forms';
import { EquipamientoAdminService } from "src/app/services/equipamientoAdmin-service/equipamiento-admin.service";
import { EnumTipo } from "src/modelo/EnumTipo";
import { EnumCatEquipo } from "src/modelo/EnumCatEquipo";
import { EnumPropiedadEquipo } from "src/modelo/EnumPropiedadEquipo";
import { EnumModificador } from "src/modelo/EnumModificador";
import { IEquipo } from "src/modelo/IEquipo";

@Component({
  selector: 'dialogCrearEquipo',
  templateUrl: './dialogCrearEquipo.html',
  styleUrls: ['./dialogCrearEquipo.css']
})
export class DialogCrearEquipo{
  constructor(public dialogRef:MatDialogRef<EquipamientoComponent>,private equipamientoServicio:EquipamientoAdminService){}

  tipos=Object.values(EnumTipo)
  categorias=Object.values(EnumCatEquipo)
  propiedades=Object.values(EnumPropiedadEquipo)
  modificadores=Object.values(EnumModificador)

  equipamiento!: IEquipo;

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
