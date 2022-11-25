import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient)
  {

  }

  trylogin(){
    this.http.get('https://127.0.0.1:5189/api/dndtools/usuarios/trylogin').subscribe(data => {
      console.log(data);
    });

  }

}
