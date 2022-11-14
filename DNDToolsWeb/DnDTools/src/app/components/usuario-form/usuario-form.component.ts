import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from "../../services/usuario-service/usuario-service.service"; //  Importo mi servicio
import { NgForm } from '@angular/forms';

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {
  constructor(private usuarioService: UsuarioServiceService) {   //esto es la inyeccion propia
   }

  ngOnInit(): void {
    
  }

  submitedFormUsuario(usuarioForm: NgForm)
  {
    this.usuarioService.annadirUsuario(usuarioForm.value).subscribe(
      (data:any) =>{
        alert(`${data} ${usuarioForm.value}}`)
      })

    console.log("Insertando el usuario: ", usuarioForm.value)

  }

}
