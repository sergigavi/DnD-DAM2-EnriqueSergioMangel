import { Injectable } from '@angular/core';
import { UsuarioComponent } from '../usuario/usuario.component';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {

  usuarios = new Set();
  //usuario: UsuarioComponent = new UsuarioComponent();

  constructor(private http: HttpClient)
  {

  }

  annadirUsuario(usuario: UsuarioComponent)
  {
    this.usuarios.add(usuario);
  };

  eliminarUsuario(usuario: UsuarioComponent)
  {
    this.usuarios.delete(usuario);
  };

  getUsuarios()
  {
    this.http.get('http://127.0.0.1:8080/API/dndtools/usuarios/dametodos')
    .subscribe(data => {  //  el subscribe es como las promesas de javascript (fetch)
    console.log(data);
  });
  }

}
