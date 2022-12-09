import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})


export class PerfilComponent implements OnInit  {
  title = 'Perfil';
  idCurrentUser="";

  constructor(private auth:AuthServiceService,private router:Router){}

  ngOnInit(): void {
    this.getCurrenUser()
    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

}
