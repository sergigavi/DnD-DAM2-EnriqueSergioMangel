import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-inicioGM',
  templateUrl: './inicioGM.component.html',
  styleUrls: ['./inicioGM.component.css']
})


export class InicioGMComponent implements OnInit  {
  title = 'Inicio GM';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

