import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LPloginComponent } from 'src/app/component/login/lplogin/lplogin.component';
import { IUsuario } from 'src/modelo/IUsuario';
import {Router} from '@angular/router';
import { AuthServiceService } from '../auth-user/auth-service.service';
import { UsuarioServiceService } from '../usuario-service/usuario-service.service';
import { AdministradorService } from '../administrador-service/adminstrador.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  idCurrentUser:String=""
  constructor(private http: HttpClient, private router:Router,private auth:AuthServiceService,private userService:UsuarioServiceService,
    private adminService:AdministradorService)
  {

  }

  trylogin(usuario:any){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    this.adminService.existsByEmail(usuario.email).subscribe((data:any)=>{
      
    })

    return this.http.get(`${environment.URLBASE}/usuarios/trylogin/${usuario.email}/${usuario.contrasenia}`,{headers:headers, responseType:'json', withCredentials:false})
    .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
      if (data != null){
        var id = data.idUserString
        this.auth.sendData(id)
        this.getCurrenUser()
        if(this.adminService.existsById(this.idCurrentUser)){

        }
        this.router.navigate(['/'])
      }else{
        this.router.navigate(['inicio'])
      }
    });
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:String)=>{
      this.idCurrentUser=data;
    })
  }
}
