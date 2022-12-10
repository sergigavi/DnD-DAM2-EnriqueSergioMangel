import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';
import { Router } from '@angular/router';
import { IUsuario } from 'src/modelo/IUsuario';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-lplogin',
  templateUrl: './lplogin.component.html',
  styleUrls: ['./lplogin.component.css'],
  providers: [LoginService]
})

export class LPloginComponent {

  email: string;
  contrasenia: string;

  constructor(private loginService: LoginService,private router: Router)
  {
    this.email = "";
    this.contrasenia = "";
  }

  trylogin()
  {

    const usuario :any = {
      email:this.email,
      contrasenia:this.contrasenia
    }

    //console.log(usuario)
    //  Esto llama a la api
    this.loginService.trylogin(usuario)

  }

  ngOnInit(){
    //this.loginService.trylogin()
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }


}
