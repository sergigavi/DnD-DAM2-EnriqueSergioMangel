import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-heroesGM',
  templateUrl: './heroesGM.component.html',
  styleUrls: ['./heroesGM.component.css']
})


export class HeroesGMComponent implements OnInit  {
  title = 'Heroes GM';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

