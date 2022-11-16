import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-inicioJugador',
  templateUrl: './inicioJugador.component.html',
  styleUrls: ['./inicioJugador.component.css']
})


export class InicioJugadorComponent implements OnInit  {
  title = 'Inicio Jugador';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

