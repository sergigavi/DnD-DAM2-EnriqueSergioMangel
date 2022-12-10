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
    this.adminService.existsByEmail(usuario.email).subscribe((data:any)=>{

    })

    return this.http.get(`${environment.URLBASE}/usuarios/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
    .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
      if (data != null){
        var id = data.idUserString
        this.auth.sendData(id)
        this.getCurrenUser()

        //me guardo el id en la cookie
        this.cookieService.set("CurrentUserId",id)

        if(false){//this.adminService.existsById(this.idCurrentUser)){
          //panel admin
          //this.router.navigate(['/'])
        }
        else{
          this.router.navigate(['inicio'])
        }
      }else{
        this.router.navigate(['/'])
      }
    });
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:String)=>{
      this.idCurrentUser=data;
    })
  }
}
