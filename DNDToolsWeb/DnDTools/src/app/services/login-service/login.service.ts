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
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  idCurrentUser:String=""
  constructor(private http: HttpClient, private router:Router,private auth:AuthServiceService,private userService:UsuarioServiceService,
    private adminService:AdministradorService, private cookieService: CookieService)
  {

  }

  async trylogin(usuario:any){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    //borro las cookies que haya
    this.cookieService.deleteAll()

    if (await this.esAdmin(usuario.email))
    {
      console.log("entra admin")
      this.http.get(`${environment.URLBASE}/admins/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
      .subscribe((data:any) => {

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
      console.log("entra user")

      this.http.get(`${environment.URLBASE}/usuarios/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
      .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
        if (data != null){
          var id = data.idUserString
          this.auth.sendData(id)
          this.getCurrenUser()

          //me guardo el id en la cookie
          this.cookieService.set("CurrentUserId",id)

          this.router.navigate(['/inicio'])
        }else{
          alert("Error conectando con el servidor")
          this.router.navigate(['/'])
        }
      });
    }

  }

  async esAdmin(usuario: string) :Promise<boolean>{

    var esAdmin = false
    await this.adminService.existsByEmail(usuario).subscribe((data:any) => {
      console.log(data)
      esAdmin = data
    })
    //return await this.adminService.existsByEmail(usuario).toPromise() || false
    console.log(esAdmin)
    return esAdmin
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:String)=>{
      this.idCurrentUser=data;
    })
  }
}
