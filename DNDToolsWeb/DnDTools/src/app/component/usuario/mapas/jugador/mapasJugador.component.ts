import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-mapasJugador',
  templateUrl: './mapasJugador.component.html',
  styleUrls: ['./mapasJugador.component.css']
})


export class MapasJugadorComponent implements OnInit  {
  title = 'Mapas Jugador';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

