import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PartidaServiceService } from 'src/app/services/partida-service/partida-service.service';
import { IPartida } from 'src/modelo/IPartida';

@Component({
  selector: 'app-partidas',
  templateUrl: './partidas.component.html',
  styleUrls: ['./partidas.component.css']
})


export class PartidasUsuarioComponent implements OnInit  {
  title = 'Partidas';
  opened = false;
  partida!:IPartida

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator
  @ViewChild('sort',{static:true}) sort!:MatSort

  columnas:String[] = ["nombre","acceder"]
  dataSource = new MatTableDataSource<IPartida>([]);

  constructor(private router:Router,private partidaServicio:PartidaServiceService){}

  ngOnInit() {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showPartidas()
  }

  showPartidas(){
    this.partidaServicio.getAll().subscribe((data:any)=>{
      this.dataSource.data=data
    })
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  crearPartida(){
    
  }

}


