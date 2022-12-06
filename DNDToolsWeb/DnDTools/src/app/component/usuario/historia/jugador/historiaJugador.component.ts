import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-historiaJugador',
  templateUrl: './historiaJugador.component.html',
  styleUrls: ['./historiaJugador.component.css']
})


export class HistoriaJugadorComponent implements OnInit  {
  title = 'Historia Jugador';
  opened = false;

  constructor(private router:Router){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

