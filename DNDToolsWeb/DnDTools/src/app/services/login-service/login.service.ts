import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LPloginComponent } from 'src/app/component/login/lplogin/lplogin.component';
import { IUsuario } from 'src/modelo/IUsuario';
import {Router} from '@angular/router';
import { AuthServiceService } from '../auth-user/auth-service.service';
import { UsuarioServiceService } from '../usuario-service/usuario-service.service';
import { AdministradorService } from '../administrador-service/adminstrador.service';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  idCurrentUser:String=""
  constructor(private http: HttpClient, private router:Router,private auth:AuthServiceService,private userService:UsuarioServiceService,
    private adminService:AdministradorService, private cookieService: CookieService)
  {

  }

  trylogin(usuario:any){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    //borro las cookies que haya
    this.cookieService.deleteAll()

    if(!!this.esAdmin(usuario.email) == true)
    {

      this.http.get(`${environment.URLBASE}/admins/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
      .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
        if (data != null){
          var id = data.idAdminString
          this.auth.sendData(id)
          this.getCurrenUser()

          //me guardo el id en la cookie
          this.cookieService.set("CurrentUserId",id)

          //panel admin
          this.router.navigate(['/panelControl-admin'])

        }else{
          alert("Error conectando con el servidor")
          this.router.navigate(['/'])
        }
      });
    }
    else{
      this.http.get(`${environment.URLBASE}/usuarios/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
      .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
        if (data != null){
          var id = data.idUserString
          this.auth.sendData(id)
          this.getCurrenUser()

          //me guardo el id en la cookie
          this.cookieService.set("CurrentUserId",id)

          this.router.navigate(['/'])
        }else{
          alert("Error conectando con el servidor")
          this.router.navigate(['/'])
        }
      });
    }

  }

  esAdmin(usuario: string) :any{

    this.adminService.existsByEmail(usuario).subscribe((data:any) => {
      return !!data
    })
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:String)=>{
      this.idCurrentUser=data;
    })
  }
}
