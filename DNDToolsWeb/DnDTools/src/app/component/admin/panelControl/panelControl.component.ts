import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Administrador, AdministradorService } from 'src/app/services/administrador-service/adminstrador.service';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';

@Component({
  selector: 'app-panelControl',
  templateUrl: './panelControl.component.html',
  styleUrls: ['./panelControl.component.css']
})


export class PanelControlComponent implements OnInit  {
  title = 'PanelControl';
  opened = false;
  idCurrentUser="";

  constructor(private auth:AuthServiceService,private router:Router,private adminService:AdministradorService){}

  ngOnInit(): void {
    this.getCurrenUser()
    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }else{
      this.adminService.existsById(this.idCurrentUser).subscribe((data:any)=>{
        if(data==false){
          this.router.navigate(['login'])
        }
      })
    }
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

}

