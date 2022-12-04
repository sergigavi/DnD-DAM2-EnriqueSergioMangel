import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';
import { Usuario } from 'src/app/services/usuario-service/usuario-service.service';

@Component({
  selector: 'app-lplogin',
  templateUrl: './lplogin.component.html',
  styleUrls: ['./lplogin.component.css'],
  providers: [LoginService]
})

export class LPloginComponent {

  nickname: string;
  contrasenia: string;

  constructor(private loginService: LoginService)
  {
    this.nickname = "";
    this.contrasenia = "";
  }

  trylogin()
  {

    const usuario :Usuario = {
      nickname:this.nickname,
      contrasenia:this.contrasenia
    }

    //console.log(usuario)
    //  Esto llama a la api
    this.loginService.trylogin(usuario)
  }

  ngOnInit(){
    //this.loginService.trylogin()
  }



}
