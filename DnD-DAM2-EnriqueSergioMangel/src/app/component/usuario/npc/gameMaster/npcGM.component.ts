import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-npcGM',
  templateUrl: './npcGM.component.html',
  styleUrls: ['./npcGM.component.css']
})


export class NpcGMComponent implements OnInit  {
  title = 'Npc GM';
  opened = false;

  constructor(){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

