import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-equipamiento',
  templateUrl: './equipamiento.component.html',
  styleUrls: ['./equipamiento.component.css']
})


export class EquipamientoComponent implements OnInit  {
  title = 'Equipamiento';
  opened = false;

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }
}
