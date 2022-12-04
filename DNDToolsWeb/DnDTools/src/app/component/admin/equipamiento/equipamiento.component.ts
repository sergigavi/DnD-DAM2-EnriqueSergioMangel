import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { DialogCrearEquipo } from './DialogCrearEquipo';

export interface EquipamientoAdmin{
  nombre:String;
  tipo:Tipo;
  categoria:CatEquipo;
  propiedad:PropiedadEquipo[];
  modificador:String;
  danio:String;
  alcance:number;
  precio:String;
  peso:String;
  descripcion:String;
}

export enum Tipo{
  ARMADURA, ARMA, EQUIPO_DE_AVENTURAS
}

export enum CatEquipo{
  ARMADURA_INTERMEDIA, ARMADURA_LIGERA, ARMADURA_PESADA,
	ARMA_MARCIAL, ARMA_SENCILLA, ESCUDO, HERRAMIENTA, INSTRUMENTO_MUSICAL,
	JUEGO, KIT, MONTURA, MUNICION, OTROS, PAQUETE_DE_EQUIPO, VEHICULO
}

export enum PropiedadEquipo{
  ALCANCE, ARROJADIZO, CARGADOR, DE_CARGA, DESVENTAJA, DISTANCIA, DOS_MANOS, ESPECIAL, FOCO_ARCANO, FOCO_DUIDRICO,
	FUERZA, LIGERO, MUNICION, MUNICION_ESPECIAL, PESADO, RAFAGA, SIMBOLO_SAGRADO, SINTONIZADO, SUTIL, VERSATIL
}


@Component({
  selector: 'app-equipamiento',
  templateUrl: './equipamiento.component.html',
  styleUrls: ['./equipamiento.component.css']
})


export class EquipamientoComponent implements OnInit  {
  title = 'Equipamiento';
  opened = false;

  //data nombrado aqui
  data:EquipamientoAdmin[]=[];
  columnas:String[] = ["nombre","tipo","categoria","acceder","editar"]

  @ViewChild('paginator',{static:true}) paginator!:MatPaginator

  dataSource = new MatTableDataSource<EquipamientoAdmin>([])

  constructor(private dialog : MatDialog,private equipamientoService: EquipamientoAdminService,private router:Router){
    //esto hace que se envie directamente a la tabla
    this.equipamientoService.getAll().subscribe(x=>{
      this.data = x;
      console.log(this.data);
    })
  }

  ngOnInit(): void {
    this.showEquipo()
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  public showEquipo(){
    this.equipamientoService.getAll().subscribe((data:any)=>{
      console.log(data)
      this.dataSource.data=data
    })
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  public crearEquipo(){

    const dialogRef=this.dialog.open(DialogCrearEquipo,{
      width: '100%',
    });

    dialogRef.afterClosed().subscribe((data:any)=>{
      console.log("Cerrado")
      this.showEquipo();
    })
  }
}
