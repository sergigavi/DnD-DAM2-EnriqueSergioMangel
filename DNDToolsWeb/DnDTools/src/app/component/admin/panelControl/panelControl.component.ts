import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-panelControl',
  templateUrl: './panelControl.component.html',
  styleUrls: ['./panelControl.component.css']
})


export class PanelControlComponent implements OnInit  {
  title = 'PanelControl';
  opened = false;

  constructor(private router:Router){}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

}

