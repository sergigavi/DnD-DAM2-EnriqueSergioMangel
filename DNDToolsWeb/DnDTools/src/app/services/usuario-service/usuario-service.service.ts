import { Injectable } from '@angular/core';
import { Usuario } from '../../modelo/usuario/usuario.component';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {


  constructor(private http: HttpClient)
  {

  }

  annadirUsuario(usuario: Usuario)
  {
    this.http.post(`${environment.URLBASE}/usuarios/insertarUser`, usuario)
    .subscribe((data:any) => {
      console.log(data)
    })

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
