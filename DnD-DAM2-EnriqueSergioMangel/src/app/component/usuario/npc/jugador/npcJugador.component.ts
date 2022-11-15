import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-npcJugador',
  templateUrl: './npcJugador.component.html',
  styleUrls: ['./npcJugador.component.css']
})


export class NpcJugadorComponent implements OnInit  {
  title = 'Npc Jugador';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

