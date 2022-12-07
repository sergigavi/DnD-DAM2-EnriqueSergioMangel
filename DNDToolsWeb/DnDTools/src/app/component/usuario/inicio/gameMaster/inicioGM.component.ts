import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicioGM',
  templateUrl: './inicioGM.component.html',
  styleUrls: ['./inicioGM.component.css']
})


export class InicioGMComponent implements OnInit  {
  title = 'Inicio GM';
  opened = false;

  constructor(private router:Router){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

