import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { FichaAdminService } from 'src/app/services/fichaPersonaje-service/ficha-admin.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { EnumAlineamiento } from 'src/modelo/EnumAlineamiento';
import { EnumClase } from 'src/modelo/EnumClase';
import { EnumRaza } from 'src/modelo/EnumRaza';
import { IEquipo } from 'src/modelo/IEquipo';
import { IFichaPersonaje } from 'src/modelo/IFichaPersonaje';
import { IHabilidad } from 'src/modelo/IHabilidad';
import { EnumHabilidad } from 'src/modelo/EnumHabilidad';
import { ICaracteristica } from 'src/modelo/ICaracteristica';


/**
 * @title Data table with sorting, pagination, and filtering.
 */

@Component({
  selector: 'app-fichas',
  templateUrl: './fichasAdmin.html',
  styleUrls: ['./fichasAdmin.css']
})


export class FichasAdmin implements OnInit  {
  title = 'Fichas de Personaje';
  opened = false;
  ficha!:IFichaPersonaje

  columnas:String[]=["nombre","usuario","nivel","clase","acceder","editar"]

  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  dataSource= new MatTableDataSource<IEquipo[]>([]);

  constructor(private fichaService:FichaAdminService,private router:Router,private dialog:MatDialog,
    private equipoService:EquipamientoAdminService){}

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value
    this.dataSource.filter = filterValue.trim().toLowerCase()

    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage()
    }
  }



  ngOnInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showFichas()
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
  }

  showFichas(){
    this.fichaService.getAll().subscribe((data:any)=>{
      this.dataSource.data=data
    })
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  crearFicha(){
    const dialogRef=this.dialog.open(DialogCrearFicha,{
      width: '100%',
    });

    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showFichas();
    });
  }

  verFicha(ficha:IFichaPersonaje){

  }

  editarFicha(ficha:IFichaPersonaje){

  }

}

@Component({
  selector:'dialogCrearFicha',
  templateUrl:'dialogCrearFicha.html',
  styleUrls:['dialogCrearFicha.css']
})
export class DialogCrearFicha{
  constructor(public dialogRef:MatDialogRef<FichasAdmin>,private fichaServicio:FichaAdminService,
    private equipoService:EquipamientoAdminService,
    private usuarioService:UsuarioServiceService){}

  step =0;
  numberControlFue = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlDes = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCon = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlInt = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlSab = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCar = new FormControl("Error",[Validators.min(3),Validators.max(20)])


  setStep(index:number){
    this.step=index;
  }

  nextStep(){
    this.step++;
  }

  previousStep(){
    this.step--;
  }

  clases=Object.values(EnumClase)
  razas=Object.values(EnumRaza)
  alineamientos=Object.values(EnumAlineamiento)

  fueElegida:number=10;
  desElegida:number=10;
  conElegida:number=10;
  intElegida:number=10;
  sabElegida:number=10;
  carElegida:number=10;
  fuerza:ICaracteristica={
    nombre:"Fuerza",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Fue"
  }
  destreza:ICaracteristica={
    nombre:"Destreza",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Des"
  }
  constitucion:ICaracteristica={
    nombre:"Constitucion",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Con"
  }
  inteligencia:ICaracteristica={
    nombre:"Inteligencia",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Int"
  }
  sabiduria:ICaracteristica={
    nombre:"Sabiduria",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Sab"
  }
  carisma:ICaracteristica={
    nombre:"Carisma",
    valorMod:0,
    valorTotal:0,
    nombreIniciales:"Car"
  }


  atletismo:IHabilidad={
    nombre:"Atletismo",
    competencia:false,
    mod:0
  }
  cArcano:IHabilidad={
    nombre:"C_Arcanos",
    competencia:false,
    mod:0
  }
  enganiar:IHabilidad={
    nombre:"Enganiar",
    competencia:false,
    mod:0
  }
  historia:IHabilidad={
    nombre:"Historia",
    competencia:false,
    mod:0
  }
  interpretacion:IHabilidad={
    nombre:"Interpretacion",
    competencia:false,
    mod:0
  }
  intimidar:IHabilidad={
    nombre:"Intimidar",
    competencia:false,
    mod:0
  }
  investigar:IHabilidad={
    nombre:"Investigar",
    competencia:false,
    mod:0
  }
  juegoDeManos:IHabilidad={
    nombre:"Juego_de_manos",
    competencia:false,
    mod:0
  }
  medicina:IHabilidad={
    nombre:"Medicina",
    competencia:false,
    mod:0
  }
  naturaleza:IHabilidad={
    nombre:"Naturaleza",
    competencia:false,
    mod:0
  }
  percepcion:IHabilidad={
    nombre:"Percepcion",
    competencia:false,
    mod:0
  }
  perspicacia:IHabilidad={
    nombre:"Perspicacia",
    competencia:false,
    mod:0
  }
  persuasion:IHabilidad={
    nombre:"Persuasion",
    competencia:false,
    mod:0
  }
  religion:IHabilidad={
    nombre:"Religion",
    competencia:false,
    mod:0
  }
  sigilo:IHabilidad={
    nombre:"Sigilo",
    competencia:false,
    mod:0
  }
  supervivencia:IHabilidad={
    nombre:"Supervivencia",
    competencia:false,
    mod:0
  }
  tratoConAnimales:IHabilidad={
    nombre:"Trato_con_animales",
    competencia:false,
    mod:0
  }
  acrobacias:IHabilidad={
    nombre:"Acrobacias",
    competencia:false,
    mod:0
  }


  ficha:any



  onNoClick(){
    this.dialogRef.close()
  }

  ngOnInit(){

  }

  ngOnSubmit(fichaForm:NgForm){

    this.ficha=fichaForm.value
    console.log(this.ficha)
    this.fuerza.valorTotal=this.fueElegida
    this.destreza.valorTotal=this.desElegida
    this.constitucion.valorTotal=this.conElegida
    this.inteligencia.valorTotal=this.intElegida
    this.sabiduria.valorTotal=this.sabElegida
    this.carisma.valorTotal=this.carElegida

    var car = [this.fuerza,this.destreza,this.constitucion,this.inteligencia,this.sabiduria,this.carisma]

    var hab =[this.acrobacias,this.cArcano,this.enganiar,this.historia,this.interpretacion,this.intimidar
    ,this.investigar,this.juegoDeManos,,this.atletismo,this.medicina,this.naturaleza,this.percepcion,this.perspicacia
    ,this.persuasion,this.religion,this.sigilo,this.supervivencia,this.tratoConAnimales]

    this.ficha.caracteristicas=car
    this.ficha.habilidades=hab
    
    console.log(this.ficha)
    this.dialogRef.close()
  }
}
