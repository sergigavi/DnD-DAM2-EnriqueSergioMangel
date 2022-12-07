import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';
import { IUsuario } from 'src/modelo/IUsuario';

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

    const usuario :any = {
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
