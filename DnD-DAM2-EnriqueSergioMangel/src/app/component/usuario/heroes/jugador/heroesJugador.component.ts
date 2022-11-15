import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-heroesJugador',
  templateUrl: './heroesJugador.component.html',
  styleUrls: ['./heroesJugador.component.css']
})


export class HeroesJugadorComponent implements OnInit  {
  title = 'Heroes Jugador';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

