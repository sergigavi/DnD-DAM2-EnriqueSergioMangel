import {Component, OnInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { PartidaServiceService } from 'src/app/services/partida-service/partida-service.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
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

  constructor(private usuarioService:UsuarioServiceService,private router:Router,private partidaServicio:PartidaServiceService,private dialog : MatDialog,private auth:AuthServiceService){}

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
    const dialogRef=this.dialog.open(DialogCrearPartida)

    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showPartidas()
    })
  }

}

@Component({
  selector:'dialogCrearPartida',
  templateUrl:'dialogCrearPartida.html'
})
export class DialogCrearPartida{

  constructor(private usuarioService:UsuarioServiceService,private dialogRef:MatDialogRef<PartidasUsuarioComponent>,private auth:AuthServiceService){}
  partida:any
  idCurrentUser="";
  currentuser:any
  nombrePartida:String=""

  onSubmit(ngForm:NgForm){
    console.log(ngForm.value)
    this.partida=ngForm
    this.getCurrenUser()
    this.usuarioService.getUsuario(this.idCurrentUser).subscribe((data:any)=>{
      this.currentuser=data
      console.log(this.currentuser)
      this.dialogRef.close();
    })

  }

  onNoClick(){
    this.dialogRef.close()
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }
}


