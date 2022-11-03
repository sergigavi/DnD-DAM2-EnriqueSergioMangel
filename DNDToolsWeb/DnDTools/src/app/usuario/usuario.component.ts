import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  private idUser:String;
  private nombre:String;
  private apellidos:String;
  private contrasenia:String;
  private nickname:String;
  private biografia:String;
  private email:String;
  private fechaNac:String;
  private urlImage:String;
  private activo:boolean;
  private pais:String;

  constructor() {

    this.idUser = "";
    this.nombre = "";
    this.apellidos = "";
    this.contrasenia = "";
    this.nickname = "";
    this.biografia = "";
    this.email = "";
    this.fechaNac = "";
    this.urlImage = "";
    this.activo = false;
    this.pais = "";
  }

  ngOnInit(): void {
  }


  //  Getters

  public getIdUser() : String {
    return this.idUser;
  }

  public getNombre() : String{
    return this.nombre;
  }

  public getApellidos() : String{
    return this.apellidos;
  }

  public getContrasenia() : String{
    return this.contrasenia;
  }

  public getNickname() : String{
    return this.nickname;
  }

  public getBiografia() : String{
    return this.biografia;
  }

  public getEmail() : String{
    return this.email;
  }

  public getFechaNac() : String{
    return this.fechaNac;
  }

  public getUrlImage() : String{
    return this.urlImage;
  }

  public isActivo() : boolean{
    return this.activo;
  }

  public getPais() : String{
    return this.pais;
  }

  //  Setters

  public setIdUser(idUser:String){
    this.idUser = idUser;
  }

  public setNombre(nombre:String){
    this.nombre = nombre;
  }


}
