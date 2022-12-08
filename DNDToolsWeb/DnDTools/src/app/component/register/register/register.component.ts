import { Component } from '@angular/core';
import { RegisterService } from 'src/app/services/register-service/register.service';
import { IUsuario } from 'src/modelo/IUsuario';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService]

})
export class RegisterComponent {

  nombre:String;
  apellidos:String;
  contrasenia:any;
  nickname:any;
  biografia:String;
  email:String;
  fechaNacimiento:String;
  urlImage:String;
  activo:boolean;
  pais:String;

  constructor(private registerService: RegisterService)
  {
    this.nombre = "";
    this.apellidos= "";
    this.nickname = "";
    this.contrasenia = "";
    this.biografia = "";
    this.email = "";
    this.fechaNacimiento = "";
    this.urlImage = "";
    this.activo = false;
    this.pais= "";
  }

  tryRegister()
  {

    const usuario :IUsuario = {
      idUser:null,
      idUserString:"",
      nombre:this.nombre,
      apellidos:this.apellidos,
      nickname:this.nickname,
      contrasenia:this.contrasenia,
      biografia:this.biografia,
      email:this.email,
      fechaNacimiento:this.fechaNacimiento,
      urlImage:this.urlImage,
      activo:this.activo,
      pais:this.pais
    }

    //console.log(usuario)
    //  Esto llama a la api
    this.registerService.register(usuario)
  }

  ngOnInit(){

  }

}
