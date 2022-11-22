import {Component, OnInit} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';

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

  columnas:String[] = ["Nombre","Descripcion"]

  dataSource = new MatTableDataSource<EquipamientoAdmin>([])

  constructor(private equipamientoService: EquipamientoAdminService){

  }

  ngOnInit(): void {
    this.showEquipo()
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }


  public showEquipo(){
    this.equipamientoService.getAll().subscribe((data:any)=>{
      this.dataSource=data
    })
  }
}
