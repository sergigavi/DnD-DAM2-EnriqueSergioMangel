import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { DialogCrearEquipo } from './DialogCrearEquipo';

export interface EquipamientoAdmin{
  nombre:String;
  tipo:String;
  categoria:String;
  propiedad:String;
  modificador:String;
  danio:String;
  alcance:String;
  precio:String;
  peso:String;
  descripcion:String;
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
  columnas:String[] = ["nombre","descripcion"]

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

    })
  }
}
