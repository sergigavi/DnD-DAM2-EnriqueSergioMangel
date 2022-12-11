import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { FichaAdminService } from 'src/app/services/fichaPersonaje-service/ficha-admin.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { Administrador, AdministradorService } from 'src/app/services/administrador-service/adminstrador.service';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-panelControl',
  templateUrl: './panelControl.component.html',
  styleUrls: ['./panelControl.component.css']
})


export class PanelControlComponent implements OnInit  {
  title = 'PanelControl';
  opened = false;
  idCurrentUser="";
  usuario=0;
  equipamiento=0;
  fichas=0;

  constructor(private auth:AuthServiceService,private router:Router, private cookieService: CookieService,private adminService:AdministradorService,private usuarioServicio: UsuarioServiceService,private equipamientoService: EquipamientoAdminService,
    private fichaService:FichaAdminService){}

  ngOnInit(): void {
    this.getCurrenUser()

    if(!this.cookieService.check("CurrentAdminId")){
      this.auth.deleteData()
      this.router.navigate(['/'])
    }

    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }else{
      this.adminService.existsById(this.idCurrentUser).subscribe((data:any)=>{
        if(data==false){
          this.router.navigate(['login'])
        }
      })
    }

    this.usuarioServicio.getCantidad().subscribe((data:any)=>{
      this.usuario=data
    })

    this.equipamientoService.getCantidad().subscribe((data:any)=>{
      this.equipamiento=data
    })

    this.fichaService.getCantidad().subscribe((data:any)=>{
      this.fichas=data
    })

  }

  cerrarSesionAdmin()
  {
    this.cookieService.deleteAll()
    this.router.navigate(["/"])
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

