import {Component, OnInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { PartidaServiceService } from 'src/app/services/partida-service/partida-service.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { IPartida } from 'src/modelo/IPartida';
import { IUsuario } from 'src/modelo/IUsuario';

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
  idCurrentUser="";
  constructor(private cookieService: CookieService,private usuarioService:UsuarioServiceService,private router:Router,private partidaServicio:PartidaServiceService,private dialog : MatDialog,private auth:AuthServiceService){}

  ngOnInit() {
    this.getCurrenUser()
    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }
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

  getCurrenUser(){
    this.idCurrentUser=this.cookieService.get("CurrentUserId");
  }

}

@Component({
  selector:'dialogCrearPartida',
  templateUrl:'dialogCrearPartida.html'
})
export class DialogCrearPartida{

  constructor(private partidaServicio: PartidaServiceService, private cookieService: CookieService,private usuarioService:UsuarioServiceService,private dialogRef:MatDialogRef<PartidasUsuarioComponent>,private auth:AuthServiceService){}
  par:any
  partida!:IPartida
  idCurrentUser="";
  currentuser!:IUsuario
  nombrePartida:String=""

  onSubmit(ngForm:NgForm){
    this.par=ngForm.value
    this.partida=this.par
    this.getCurrenUser()

    this.usuarioService.getUsuario(this.idCurrentUser).subscribe((data:any)=>{
      this.currentuser=data
      console.log(this.currentuser)
      console.log(this.partida)
      this.partidaServicio.addPartida(this.currentuser,this.partida).subscribe((data:any)=>{

      })
      this.dialogRef.close();
    })

  }

  onNoClick(){
    this.dialogRef.close()
  }



  getCurrenUser(){
    this.idCurrentUser=this.cookieService.get("CurrentUserId");
  }
}


