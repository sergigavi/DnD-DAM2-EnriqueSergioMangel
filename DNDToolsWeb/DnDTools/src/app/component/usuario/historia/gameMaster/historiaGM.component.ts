import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-historiaGM',
  templateUrl: './historiaGM.component.html',
  styleUrls: ['./historiaGM.component.css']
})


export class HistoriaGMComponent implements OnInit  {
  title = 'Historia GM';
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

