import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LPloginComponent } from 'src/app/component/login/lplogin/lplogin.component';
import { IUsuario } from 'src/modelo/IUsuario';
import {Router} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthServiceService } from '../auth-user/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http: HttpClient, private router:Router,private auth:AuthServiceService)
  {

  }

  getCurrentUser(){

  }

  trylogin(usuario:IUsuario){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    //headers.append('Accept', '*/*');
    //headers.append('Accept-Encoding', 'gzip, deflate, br');

    /*var params = new HttpParams();
    params = params.append('nickname', nickname.toString); //vale set y append
    params = params.append('contrasenia', contrasenia.toString());*/

    /*options: {
      headers: headers,  //  HttpHeaders | {[header: string]: string | string[]}
      //observe?: 'body' | 'events' | 'response',
      params: params,  //HttpParams|{[param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>}
      //reportProgress?: boolean,
      responseType: 'json'  //  'arraybuffer'|'blob'|'json'|'text'
      //body: data,
      //withCredentials: false
    }*/

    //debugger;
    //hago la llamada a la api
    return this.http.post('http://127.0.0.1:5189/api/dndtools/usuarios/trylogin', usuario, {headers:headers, responseType:'json', withCredentials:false})
    .subscribe((data:any) => {  //data es la respuesta que me devuelve la api
      if (data != null){
        var id = data.idUserString
        this.auth.sendData(id)
        this.router.navigate(['/'])
      }else{
        this.router.navigate(['inicio'])
      }


      //TODO fixear esto
      //let lplogin = document.getElementById('mensajelogin')
      //lplogin.innerHTML = 'aaaaaaa'


    });



  }

}
