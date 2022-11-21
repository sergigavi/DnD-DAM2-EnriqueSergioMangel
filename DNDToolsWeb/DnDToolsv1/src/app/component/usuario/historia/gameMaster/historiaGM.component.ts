import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-historiaGM',
  templateUrl: './historiaGM.component.html',
  styleUrls: ['./historiaGM.component.css']
})


export class HistoriaGMComponent implements OnInit  {
  title = 'Historia GM';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

