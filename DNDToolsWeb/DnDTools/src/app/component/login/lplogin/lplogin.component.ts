import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-lplogin',
  templateUrl: './lplogin.component.html',
  styleUrls: ['./lplogin.component.css'],
  providers: [LoginService]
})

export class LPloginComponent {

  constructor(private loginService: LoginService)
  {

  }
}
