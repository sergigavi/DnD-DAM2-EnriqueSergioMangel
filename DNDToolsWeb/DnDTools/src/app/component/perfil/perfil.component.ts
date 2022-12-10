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

  contra:string;
  contra2:string;

  datosUser: any;

  constructor(private auth:AuthServiceService, private userService: UsuarioServiceService,private router:Router, private cookieService: CookieService){
    this.cargarDatos()
    this.contra = "";
    this.contra2 = "";
  }

  ngOnInit(): void {
    this.getCurrenUser()
    if(!this.cookieService.check("CurrentUserId")){
      this.auth.deleteData()
      this.router.navigate(['login'])
    }
    else{

    }

  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

  cambiarContrasenia()
  {

    if (this.contra == this.contra2 && this.contra != "" && this.contra2 != "" )
    {
      this.userService.cambiarContrasenia(this.cookieService.get("CurrentUserId"), this.contra).subscribe((data:any) => {
        console.log(data)
      })
      alert("Contraseña actualizada correctamente")
      this.contra = ""
      this.contra2 = ""
    }
    else{
      alert("Deben coincidir las contraseñas")
      this.contra = ""
      this.contra2 = ""
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

      this.datosUser.nombre = this.datosUser.nombre + " " + this.datosUser.apellidos

      if (this.datosUser.activo == true)
    {
      this.datosUser.activo = "Conectado recientemente";
    } else{
      this.datosUser.activo = "Conectado hace tiempo";
    }

    this.datosUser.fechaNacimiento = this.calcularEdad(this.datosUser.fechaNacimiento)


    });
  }

  calcularEdad(fecha:Date) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

}


