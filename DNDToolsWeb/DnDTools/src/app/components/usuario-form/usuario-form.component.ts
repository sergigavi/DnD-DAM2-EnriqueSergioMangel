import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from "../../services/usuario-service/usuario-service.service"; //  Importo mi servicio
import { Usuario } from '../../modelo/usuario/usuario';

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  nombre :String = "";
  apellidos :String = "";
  contrasenia :String = "";
  nickname :String = "";
  biografia :String = "";
  email :String = "";
  fechaNac :String = "";
  urlImage :String = "";
  pais :String = "";

  constructor(private usuario: Usuario, private usuarioService: UsuarioServiceService) {   //esto es la inyeccion propia
   }

  ngOnInit(): void {
  }

  submitedFormUsuario()
  {
    this.usuario.nombre = this.nombre
    this.usuario.apellidos = this.nombre
    this.usuario.contrasenia = this.contrasenia
    this.usuario.nickname = this.nickname
    this.usuario.biografia = this.biografia
    this.usuario.email = this.email
    this.usuario.fechaNac= this.fechaNac
    this.usuario.urlImage= this.urlImage
    this.usuario.pais = this.pais

    this.usuarioService.annadirUsuario(this.usuario)

    console.log("Se ha insertado el usuario: ", this.usuario)

  }

}
