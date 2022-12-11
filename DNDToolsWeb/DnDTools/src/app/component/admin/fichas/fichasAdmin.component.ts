import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
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
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { CookieService } from 'ngx-cookie-service';


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
  idCurrentUser="";
  columnas:String[]=["nombre","nivel","clase","acceder","editar"]

  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  dataSource= new MatTableDataSource<IFichaPersonaje[]>([]);

  constructor(private auth:AuthServiceService,private fichaService:FichaAdminService,private router:Router,private dialog:MatDialog,
    private equipoService:EquipamientoAdminService, private cookieService: CookieService){

    }

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value
    this.dataSource.filter = filterValue.trim().toLowerCase()

    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage()
    }
  }
  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

  ngOnInit(): void {
    this.getCurrenUser
    this.idCurrentUser=this.cookieService.get("CurrentAdminId")
    /*if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }*/
    if(!this.cookieService.check("CurrentAdminId")){
      this.auth.deleteData()
      this.router.navigate(['/'])
    }

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

  crearFichaVacia(){
    this.fichaService.addFichaVacia().subscribe((data:any)=>{
      console.log(data)
      this.showFichas()
    })
  }

  verFicha(fichaVer:IFichaPersonaje){
    const dialogRef=this.dialog.open(DialogVerFicha,{
      width:'100%',
      data:{ficha:fichaVer}
    })
  }

  editarFicha(fichaEdit:IFichaPersonaje){
    const dialogRef=this.dialog.open(DialogEditarFicha,{
      width:'100%',
      data:{ficha:fichaEdit}
    })
    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showFichas();
    });
  }
  onDeleteAll(){
    if(confirm("¿Borrar todas las fichas?")){
      this.fichaService.deleteAllFicha().subscribe((data:any)=>{
        alert(data)
        this.showFichas()
      })
    }
  }
}

@Component({
  selector:'dialogEditarFicha',
  templateUrl:'dialogEditarFicha.html',
  styleUrls:['dialogEditarFicha.css']
})
export class DialogEditarFicha{

  constructor(public dialogRef:MatDialogRef<FichasAdmin>,private fichaServicio:FichaAdminService,
    private equipoService:EquipamientoAdminService,
    private usuarioService:UsuarioServiceService,
    @Inject(MAT_DIALOG_DATA) public data:FichasAdmin){}

  dataSource = new MatTableDataSource<IEquipo[]>([]);
  ficha:any
  inventario:any
  step=0;

  numberControlFue = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlDes = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCon = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlInt = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlSab = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCar = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  nivelControl = new FormControl("",[Validators.min(1),Validators.max(20)])


  clases=Object.values(EnumClase)
  razas=Object.values(EnumRaza)
  alineamientos=Object.values(EnumAlineamiento)


  ngOnInit(): void {
    this.ficha=this.data['ficha']
    this.inventario=this.ficha.inventario
  }
  setStep(index:number){
    this.step=index;
  }

  nextStep(){
    this.step++;
  }

  previousStep(){
    this.step--;
  }

  ngOnSubmit(fichaForm:NgForm){
    console.log(this.ficha)
    this.fichaServicio.updateFicha(this.ficha).subscribe((data:any)=>{
      this.dialogRef.close()
    })
  }

  onDelete(){
    if(confirm("¿Borrar esta ficha?")){
      this.fichaServicio.deleteFicha(this.ficha.idFichaPersonajeString).subscribe((data:any)=>{
        this.dialogRef.close()
      })
    }
  }
}

@Component({
  selector:'dialogVerFicha',
  templateUrl:'dialogVerFicha.html',
  styleUrls:['dialogVerFIcha.css']
})
export class DialogVerFicha{
  constructor(public dialogRef:MatDialogRef<FichasAdmin>,private fichaServicio:FichaAdminService,
    private equipoServicio:EquipamientoAdminService,
    private usuarioServicio:UsuarioServiceService,
    @Inject(MAT_DIALOG_DATA) public data: FichasAdmin){}

  dataSource= new MatTableDataSource<IEquipo[]>([]);
  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort
  ficha:any
  inventario:any
  columnasEquipo:String[] = ["nombre","tipo","categoria","add"]

  step =0;


  ngOnInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.ficha=this.data['ficha']
    this.inventario=this.ficha.inventario
  }
  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
  }
  setStep(index:number){
    this.step=index;
  }

  nextStep(){
    this.step++;
  }

  previousStep(){
    this.step--;
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

  dataSource= new MatTableDataSource<IEquipo[]>([]);
  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  columnasEquipo:String[] = ["nombre","tipo","categoria","add"]
  idCurrentUser="";

  step =0;
  numberControlFue = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlDes = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCon = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlInt = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlSab = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  numberControlCar = new FormControl("Error",[Validators.min(3),Validators.max(20)])
  nivelControl = new FormControl("",[Validators.min(1),Validators.max(20)])
  equipamientoAdded:String[]=[]
  equipamientoAddedString:String=""
  equipamientoLista:IEquipo[]=[]

  ngOnInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showEquipo()
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
  }

  public showEquipo(){
    this.equipoService.getAll().subscribe((data:any)=>{
      this.dataSource.data=data
    })
  }


  setStep(index:number){
    this.step=index;
  }

  nextStep(){
    this.step++;
  }

  previousStep(){
    this.step--;
  }

  onAdd(equipo:IEquipo){
    this.equipamientoLista.push(equipo)
    this.equipamientoAdded.push(equipo.nombre)
    this.equipamientoAddedString=this.equipamientoAdded.toString()
    console.log(this.equipamientoAdded)
  }

  clases=Object.values(EnumClase)
  razas=Object.values(EnumRaza)
  alineamientos=Object.values(EnumAlineamiento)

  claseElegida:string="";
  razaElegida:string="";
  alineamientoElegido:string=""

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

  ngOnSubmit(fichaForm:NgForm){
    this.ficha=fichaForm.value
    this.fuerza.valorTotal=this.fueElegida
    this.destreza.valorTotal=this.desElegida
    this.constitucion.valorTotal=this.conElegida
    this.inteligencia.valorTotal=this.intElegida
    this.sabiduria.valorTotal=this.sabElegida
    this.carisma.valorTotal=this.carElegida

    var car = [this.fuerza,this.destreza,this.constitucion,this.inteligencia,this.sabiduria,this.carisma]

    var hab =[this.acrobacias,this.cArcano,this.enganiar,this.historia,this.interpretacion,this.intimidar
    ,this.investigar,this.juegoDeManos,this.atletismo,this.medicina,this.naturaleza,this.percepcion,this.perspicacia
    ,this.persuasion,this.religion,this.sigilo,this.supervivencia,this.tratoConAnimales]

    this.ficha.caracteristicas=car
    this.ficha.habilidades=hab
    this.ficha.inventario=this.equipamientoLista
    this.ficha.usuario=Object

    console.log(this.ficha)
    this.fichaServicio.addFicha(this.ficha).subscribe((data:any)=>{
      this.dialogRef.close()
    })
  }
}
