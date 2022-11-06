import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from "../services/usuario-service/usuario-service.service"; //  Importo mi servicio
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../modelo/usuario/usuario.component';

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  constructor(private usuario: Usuario) {   //esto es la inyeccion propia

   }

  ngOnInit(): void {
  }

  submitedFormUsuario()
  {

    console.log("Se ha insertado el usuario: ", this.usuario)
  }

}
