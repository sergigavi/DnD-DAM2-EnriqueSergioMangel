import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';

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
    //  Esto llama a la api
    this.loginService.trylogin(this.nickname, this.contrasenia)
  }

  ngOnInit(){
    //this.loginService.trylogin()
  }
}
