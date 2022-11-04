import { Component, OnInit } from '@angular/core';
import { UsuarioComponent } from '../usuario/usuario.component';
import { UsuarioServiceService } from "../usuario-service/usuario-service.service"; //  Importo mi servicio

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  usuarioModel = new UsuarioComponent(new UsuarioServiceService);

  constructor() {

   }

  ngOnInit(): void {
  }

  submitedFormUsuario()
  {

    console.log("Se ha insertado el usuario: ", this.usuarioModel)
  }

}
