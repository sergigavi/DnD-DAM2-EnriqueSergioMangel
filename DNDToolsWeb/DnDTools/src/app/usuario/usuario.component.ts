import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  private idUser:String;
  public nombre:String;
  public apellidos:String;
  public contrasenia:String;
  public nickname:String;
  public biografia:String;
  public email:String;
  public fechaNac:String;
  public urlImage:String;
  public activo:boolean;
  public pais:String;

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

  public setApellidos(apellidos:String){
    this.apellidos = apellidos;
  }

  public setContrasenia(contra:String){
    this.contrasenia = contra;
  }

  public setNickname(nickname:String){
    this.nickname = nickname;
  }

  public setBiografia(biografia:String){
    this.biografia = biografia;
  }

  public setEmail (email:String){
    this.email = email;
  }

  public setFechaNac(fechaNac:String){
    this.fechaNac = fechaNac;
  }

  public setUrlImage(urlImage:String){
    this.urlImage = urlImage;
  }

  public activar(activo:boolean){
    this.activo = activo;
  }

  public setPais(pais:String){
    this.pais = pais;
  }


}
