import { Component, OnInit, ViewChild } from '@angular/core';
import { UsuarioServiceService } from "../../services/usuario-service/usuario-service.service"; //  Importo mi servicio
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface Usuario {

  idUser:String;
  nombre:String;
  apellidos:String;
  contrasenia:String;
  nickname:String;
  biografia:String;
  email:String;
  fechaNacimiento:String;
  urlImage:String;
  activo:boolean;
  pais:String;

}


@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {


  columnas:String[] = ["Nombre","Email"]

  dataSource = new MatTableDataSource<Usuario>([])

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator

  constructor(private usuarioService: UsuarioServiceService) {   //esto es la inyeccion propia
   }

  ngOnInit(): void {

    this.dataSource.paginator=this.paginator
    this.showUsuarios()
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator
  }


  public submitedFormUsuario(usuarioForm: NgForm){

    this.usuarioService.annadirUsuario(usuarioForm.value).subscribe((data:any)=>{
      console.log("dentro del subscribe")
      this.showUsuarios()
    })
  }

  public showUsuarios(){
    this.usuarioService.getUsuarios().subscribe((data:any)=>{
      this.dataSource.data = data
    })
  }
}
