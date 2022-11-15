import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-partidas',
  templateUrl: './partidas.component.html',
  styleUrls: ['./partidas.component.css']
})


export class PartidasUsuarioComponent implements OnInit  {
  title = 'Partidas';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

