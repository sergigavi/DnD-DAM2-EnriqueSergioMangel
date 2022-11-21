import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})


export class UsuariosComponent implements OnInit  {
  title = 'Usuarios';
  opened = false;

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }


}
