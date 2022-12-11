import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { IUsuario } from 'src/modelo/IUsuario';

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent {

  datosUser: any;

  constructor(private userService: UsuarioServiceService,private router:Router, private cookieService: CookieService)
  {
    this.cargarDatos()
  }

  ngOnInit(): void {
    if(!this.cookieService.check("CurrentUserId")){
      this.router.navigate(['login'])
    }
    else{

    }

  }

  public cerrarSesion()
  {
    this.cookieService.deleteAll()
    this.router.navigate(['/'])
  }

  cargarDatos() {
    this.datosUser = this.userService.getUsuario(this.cookieService.get("CurrentUserId")).subscribe((data:any) => {
      this.datosUser = data;
    });
  }


  actualizarPerfil()
  {
    this.datosUser.email = null
    this.datosUser.idUser = null
    this.datosUser.idUserString = null

    this.userService.actualizarUsuarioById(this.cookieService.get("CurrentUserId"), this.datosUser).subscribe((data:any) => {
      alert("usuario actualizado correctamente")
      console.log(data)
    });
  }


}
