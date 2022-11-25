import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

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

    this.http.get('http://127.0.0.1:5189/api/dndtools/usuarios/tryloginParams', {params:params, headers:headers, responseType:'json', withCredentials:false})
    .subscribe(data => {
      console.log(data);
    });

  }

}
