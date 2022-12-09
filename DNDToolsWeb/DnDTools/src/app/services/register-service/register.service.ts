import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { IUsuario } from 'src/modelo/IUsuario';
import { AuthServiceService } from '../auth-user/auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient, private router:Router,private auth:AuthServiceService)
  {

  }

  register(usuario:IUsuario){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    return this.http.post('http://127.0.0.1:5189/api/dndtools/usuarios/insertarUsuario', usuario, {headers:headers, responseType:'json', withCredentials:false})
    .subscribe((data:any) => {
      console.log(data);
      if (data != null){
        var id = data.idUserString
        this.auth.sendData(id)
        this.router.navigate(['/'])
      }
    });
  }

}
