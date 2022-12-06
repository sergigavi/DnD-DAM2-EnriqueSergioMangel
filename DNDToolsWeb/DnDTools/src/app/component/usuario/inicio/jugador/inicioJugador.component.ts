import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-inicioJugador',
  templateUrl: './inicioJugador.component.html',
  styleUrls: ['./inicioJugador.component.css']
})


export class InicioJugadorComponent implements OnInit  {
  title = 'Inicio Jugador'

  constructor(private router:Router){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
  }

}

