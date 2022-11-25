import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LPloginComponent } from 'src/app/component/login/lplogin/lplogin.component';
import { Usuario } from '../usuario-service/usuario-service.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient)
  {

  }

  async trylogin(usuario:Usuario){

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
    return await this.http.post('http://127.0.0.1:5189/api/dndtools/usuarios/trylogin', usuario, {headers:headers, responseType:'json', withCredentials:false})
    .subscribe(data => {  //data es la respuesta que me devuelve la api
      console.log(data);



      //TODO fixear esto
      //let lplogin = document.getElementById('mensajelogin')
      //lplogin.innerHTML = 'aaaaaaa'


    });



  }

}
