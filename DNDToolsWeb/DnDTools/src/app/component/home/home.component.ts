import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
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
  idCurrentUser:String="";

  constructor(private router:Router,private auth:AuthServiceService, private cookieService: CookieService)
  {

  }

  ngOnInit(): void {
    this.getCurrenUser()
    //console.log(this.idCurrentUser)
    //if(this.idCurrentUser="vacio" || this.cookieService.get("CurrentUserId")=="" ){
      if(this.cookieService.check("CurrentUserId")){
      this.auth.deleteData()
      this.router.navigate(['perfil'])
    }

  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }
  getCurrenUser(){
    this.idCurrentUser=this.cookieService.get("CurrentUserId");
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  public cerrarSesion(){
    this.cookieService.deleteAll()
    this.router.navigate(['/'])
  }
}
