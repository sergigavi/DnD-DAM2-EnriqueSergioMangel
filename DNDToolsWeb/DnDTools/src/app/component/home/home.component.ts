import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { IUsuario } from 'src/modelo/IUsuario';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit  {
  title = 'Inicio';
  usuario:any
  showFiller = false;

  constructor(private router:Router,private auth:AuthServiceService){}

  ngOnInit(): void {
    this.getCurrenUser()
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }
  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      console.log(data)
    })
  }
}
