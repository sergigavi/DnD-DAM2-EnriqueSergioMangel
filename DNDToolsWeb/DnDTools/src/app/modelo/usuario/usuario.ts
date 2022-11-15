//import { Component, OnInit } from '@angular/core';
//import { UsuarioServiceService } from "../../services/usuario-service/usuario-service.service"; //  Importo mi servicio

export class Usuario {

  private idUser:String;
  public nombre:String;
  public apellidos:String;
  public contrasenia:String;
  public nickname:String;
  public biografia:String;
  public email:String;
  public fechaNacimiento:String;
  public urlImage:String;
  public activo:boolean;
  public pais:String;

  constructor() { //  private usuarioService: UsuarioServiceService

    this.idUser = "";
    this.nombre = "";
    this.apellidos = "";
    this.contrasenia = "";
    this.nickname = "";
    this.biografia = "";
    this.email = "";
    this.fechaNacimiento = "";
    this.urlImage = "";
    this.activo = false;
    this.pais = "";
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
    return this.fechaNacimiento;
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
    this.fechaNacimiento = fechaNac;
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
