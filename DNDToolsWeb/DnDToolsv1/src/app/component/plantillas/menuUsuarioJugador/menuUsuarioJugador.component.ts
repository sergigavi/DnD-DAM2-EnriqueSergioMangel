import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-muj',
  templateUrl: './menuUsuarioJugador.component.html',
  styleUrls: ['./menuUsuarioJugador.component.css']
})


export class MenuUsuarioJugadorComponent implements OnInit  {

  title="";
  showFiller = false;

  public setTitle(value: string):void{
    this.title=value;
  }

  constructor(){}

  ngOnInit(): void {
  }

}

