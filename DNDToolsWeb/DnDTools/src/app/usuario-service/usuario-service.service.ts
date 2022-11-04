import { Injectable } from '@angular/core';
import { UsuarioComponent } from '../usuario/usuario.component';


@Injectable({
  providedIn: 'root'
})

export class UsuarioServiceService {

  usuarios = new Set();
  //usuario: UsuarioComponent = new UsuarioComponent();

  //constructor() { }

  annadirUsuario(usuario: UsuarioComponent)
  {
    this.usuarios.add(usuario);
  };

  eliminarUsuario(usuario: UsuarioComponent)
  {
    this.usuarios.delete(usuario);
  };

}
