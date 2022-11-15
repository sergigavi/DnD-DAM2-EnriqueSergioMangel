import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-historiaJugador',
  templateUrl: './historiaJugador.component.html',
  styleUrls: ['./historiaJugador.component.css']
})


export class HistoriaJugadorComponent implements OnInit  {
  title = 'Historia Jugador';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

