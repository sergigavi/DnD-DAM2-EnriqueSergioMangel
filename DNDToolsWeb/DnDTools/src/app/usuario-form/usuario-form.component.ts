import { Component, OnInit } from '@angular/core';
import { UsuarioComponent } from '../usuario/usuario.component';
import { UsuarioServiceService } from "../usuario-service/usuario-service.service"; //  Importo mi servicio
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  public usuarioModel: UsuarioComponent;

  constructor() {
    this.usuarioModel = new UsuarioComponent(new UsuarioServiceService(HttpClient));//TODO;fix
   }

  ngOnInit(): void {
  }

  submitedFormUsuario()
  {

    console.log("Se ha insertado el usuario: ", this.usuarioModel)
  }

}
