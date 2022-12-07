import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit  {
  title = 'Inicio';

  showFiller = false;

  constructor(private router:Router){}

  ngOnInit(): void {
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

}
