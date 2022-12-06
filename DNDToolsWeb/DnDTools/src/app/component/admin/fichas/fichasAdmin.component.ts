import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
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

/**
 * @title Data table with sorting, pagination, and filtering.
 */

export interface fichaPersonaje{
  idFichaPersonaje:any,
  idFichaPersonajeString:String,

}

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

  ficha!:IFichaPersonaje;
  step =0;

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


  onNoClick(){
    this.dialogRef.close()
  }

  ngOnInit(){

  }

  ngOnSubmit(ficha:NgForm){


  }
}
