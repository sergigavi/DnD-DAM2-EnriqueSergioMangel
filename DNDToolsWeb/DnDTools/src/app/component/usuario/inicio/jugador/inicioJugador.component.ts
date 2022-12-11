import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-inicioJugador',
  templateUrl: './inicioJugador.component.html',
  styleUrls: ['./inicioJugador.component.css']
})


export class InicioJugadorComponent implements OnInit  {
  title = 'Inicio Jugador'

  constructor(private router:Router, private cookieService: CookieService)
  {

  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
    if(!this.cookieService.check("CurrentUserId")){
      this.router.navigate(['login'])
    }
  }

}

