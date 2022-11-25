import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LPloginComponent } from 'src/app/component/login/lplogin/lplogin.component';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient)
  {

  }

  trylogin(nickname:String, contrasenia:String){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    var params = new HttpParams();
    params = params.append('nickname', nickname.toString()); //vale set y append
    params = params.append('contrasenia', contrasenia.toString());

    /*options: {
      headers: headers,  //  HttpHeaders | {[header: string]: string | string[]}
      //observe?: 'body' | 'events' | 'response',
      params: params,  //HttpParams|{[param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>}
      //reportProgress?: boolean,
      responseType: 'json'  //  'arraybuffer'|'blob'|'json'|'text'
      //body: data,
      //withCredentials: false
    }*/

    //hago la llamada a la api
    this.http.get('http://127.0.0.1:5189/api/dndtools/usuarios/tryloginParams', {params:params, headers:headers, responseType:'json', withCredentials:false})
    .subscribe(data => {  //data es la respuesta que me devuelve la api
      console.log(data);

      if (data == true) {
        console.log('SE HA AUTENTIFICADO CORRECTAMENTE')
      }else{
       console.log('Error, credenciales incorrectas')
      }


      let lplogin = document.getElementById('mensajelogin')
      lplogin.innerHTML = 'aaaaaaa'


    });

  }

}
