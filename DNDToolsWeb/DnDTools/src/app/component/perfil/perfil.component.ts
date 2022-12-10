import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { IUsuario } from 'src/modelo/IUsuario';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})


export class PerfilComponent implements OnInit  {
  title = 'Perfil';
  idCurrentUser="";

  datosUser: any;
  nombre: String = "";

  constructor(private auth:AuthServiceService, private userService: UsuarioServiceService,private router:Router, private cookieService: CookieService){
    this.cargarDatos()

    alert(this.datosUser.nombre)
  }

  ngOnInit(): void {
    this.getCurrenUser()
    if(!this.cookieService.check("CurrentUserId")){
      this.auth.deleteData()
      this.router.navigate(['login'])
    }
    else{

    }

    /*if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }*/
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

  cargarDatos() {
    this.datosUser = this.userService.getUsuario(this.cookieService.get("CurrentUserId"));
//    TODO: f
    //this.nombre = this.userService.getNombreUserById(this.cookieService.get("CurrentUserId"));
  }

}


