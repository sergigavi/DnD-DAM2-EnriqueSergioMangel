import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-mapasGM',
  templateUrl: './mapasGM.component.html',
  styleUrls: ['./mapasGM.component.css']
})


export class MapasGMComponent implements OnInit  {
  title = 'Mapas GM';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

