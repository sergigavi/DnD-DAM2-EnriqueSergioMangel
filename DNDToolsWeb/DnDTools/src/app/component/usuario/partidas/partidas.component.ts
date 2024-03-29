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
  partida:any //Ipartida

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator
  @ViewChild('sort',{static:true}) sort!:MatSort

  columnas:String[] = ["nombre","acceder"]
  dataSource = new MatTableDataSource<IPartida>([]);
  idCurrentUser="";

  constructor(private usuarioService:UsuarioServiceService,private router:Router, private cookieService: CookieService,private partidaServicio:PartidaServiceService,private dialog : MatDialog,private auth:AuthServiceService){
  }

  ngOnInit() {
    this.getCurrenUser()
    this.idCurrentUser = this.cookieService.get("CurrentUserId")
    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }

    if(!this.cookieService.check("CurrentUserId")){
      this.auth.deleteData()
      this.router.navigate(['/'])
    }
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showPartidas()
  }

  cerrarSesion()
  {
    this.cookieService.deleteAll();
    this.router.navigate(['/'])

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

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

}

@Component({
  selector:'dialogCrearPartida',
  templateUrl:'dialogCrearPartida.html'
})
export class DialogCrearPartida{

  constructor(private partidaServicio: PartidaServiceService, private cookieService: CookieService,private usuarioService:UsuarioServiceService,private dialogRef:MatDialogRef<PartidasUsuarioComponent>,private auth:AuthServiceService){}
  partida!:IPartida
  idCurrentUser="";
  currentuser!:IUsuario
  nombrePartida:String=""

  onSubmit(ngForm:NgForm){
    this.partida=ngForm.value
    this.getCurrenUser()

    this.usuarioService.getUsuario(this.idCurrentUser).subscribe((data:any)=>{
      this.currentuser=data
      this.partida.creador=this.currentuser;
      this.partidaServicio.addPartida(this.partida).subscribe((data:any)=>{

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


