import { Injectable } from '@angular/core';
import { Usuario } from '../../modelo/usuario/usuario';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {


  constructor(private http: HttpClient)
  {

  }

  annadirUsuario(usuario: Usuario): Observable<String>
  {
    return this.http.post(`${environment.URLBASE}/usuarios/addUsuario`, usuario,{responseType:"text"})
  };

  eliminarUsuario(usuario: Usuario)
  {

  };

  getUsuarios()
  {

    this.http.get(`${environment.URLBASE}/usuarios/dametodos`)
    .subscribe(data => {  //  el subscribe es como las promesas de javascript (fetch)
    console.log(data);

  });
  }

}
