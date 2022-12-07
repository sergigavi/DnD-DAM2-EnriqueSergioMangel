import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
UsuariosComponent
import {  NgForm } from '@angular/forms';
import { UsuariosComponent } from "./usuarios.component";
import { IUsuario } from "src/modelo/IUsuario";
import { UsuarioServiceService } from "src/app/services/usuario-service/usuario-service.service";

@Component({
  selector: 'dialogCrearUsuario',
  templateUrl: './dialogCrearUsuario.html',
  styleUrls: ['./dialogCrearUsuario.css']
})
export class DialogCrearUsuario{
  constructor(public dialogRef:MatDialogRef<UsuariosComponent>,private usuarioServicio:UsuarioServiceService){}

  checked:string="checked"
  usuario!: IUsuario;

  onNoClick(){
    this.dialogRef.close()
  }

  ngOnInit(){

  }

  onSubmit(usuarioData:NgForm){
    this.usuario=usuarioData.value

    this.usuarioServicio.addUsuario(this.usuario).subscribe((data:any)=>{
      console.log(data)
      this.dialogRef.close()
    })
  }
}
