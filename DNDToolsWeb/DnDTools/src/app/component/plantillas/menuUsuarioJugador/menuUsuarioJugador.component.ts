import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import { HeroesJugadorComponent } from '../../usuario/heroes/jugador/heroesJugador.component';


@Component({
  selector: 'app-muj',
  templateUrl: './menuUsuarioJugador.component.html',
  styleUrls: ['./menuUsuarioJugador.component.css']
})


export class MenuUsuarioJugadorComponent implements AfterViewInit  {

  @ViewChild(HeroesJugadorComponent) child: any;

  showFiller = false;

  constructor(){}

  title:string="title";

  ngAfterViewInit(): void {
      this.title=this.child.message;
  }

  ngOnInit(): void {
  }

}

