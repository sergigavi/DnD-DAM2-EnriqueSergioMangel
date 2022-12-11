import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-historiaJugador',
  templateUrl: './historiaJugador.component.html',
  styleUrls: ['./historiaJugador.component.css']
})


export class HistoriaJugadorComponent implements OnInit  {
  title = 'Historia Jugador';
  opened = false;

  constructor(private router:Router, private cookieService:CookieService){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
    if(!this.cookieService.check("CurrentUserId")){
      this.router.navigate(['/'])
    }
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

