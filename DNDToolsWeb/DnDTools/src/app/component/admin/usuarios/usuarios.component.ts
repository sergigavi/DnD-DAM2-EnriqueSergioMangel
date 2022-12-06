import { Component, OnInit, Inject, AfterViewInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {  UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';

export interface Usuario {

  idUser?:any;
  nombre?:String;
  apellidos?:String;
  contrasenia:String;
  nickname:String;
  biografia?:String;
  email?:String;
  fechaNacimiento?:String;
  urlImage?:String;
  activo?:boolean;
  pais?:String;

}

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})

export class UsuariosComponent implements OnInit  {
  title = 'Usuarios';
  opened = false;
  usuario!:Usuario

  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  //data nombrado aqui
  data2:Usuario[]=[];
  columnas: string[] = ['nombreCuenta','correo','acceder','editar'];
  dataSource = new MatTableDataSource<Usuario>([])

  constructor(public dialog: MatDialog,private usuarioService: UsuarioServiceService){
    //esto hace que se envie directamente a la tabla
    this.usuarioService.getUsuarios().subscribe(x=>{
      this.data2 = x;
      console.log(this.data2);
    });
  }

  ngOnInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showUsuario()
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator;
    this.dataSource.sort=this.sort
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value
    this.dataSource.filter = filterValue.trim().toLowerCase()

    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage()
    }
  }

  public showUsuario(){
    this.usuarioService['getAll']().subscribe((data:any)=>{
      this.dataSource.data=data
    })
  }

  openDialog(usuario:Usuario) {
    this.usuario=usuario;
    const dialogRef = this.dialog.open(DialogUsuarios,{
      data:{usuario:this.usuario},
      width:'100%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  editarUsuario(usuario:Usuario) {
    this.usuario=usuario;
    const dialogRef = this.dialog.open(Dialog2Usuarios,{
      data:{usuario:this.usuario},
      width:'100%'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.showUsuario()
    });
  }

}

@Component({
  selector: 'usuariosDialog',
  templateUrl: 'usuariosDialog.html'
})

export class DialogUsuarios {

  usuario:any;
  constructor(public dialogRef: MatDialogRef<UsuariosComponent>,@Inject(MAT_DIALOG_DATA) public data: UsuariosComponent) {}

    ngOnInit() {
      this.usuario=this.data['usuario'];
    }
    onNoClick(){
      this.dialogRef.close()
    }
}

@Component({
  selector: 'usuariosDialog2',
  templateUrl: 'usuariosDialog2.html'
})

export class Dialog2Usuarios {
  usuario: any;

  constructor(
    public dialogRef: MatDialogRef<UsuariosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UsuariosComponent,private usuarioService: UsuarioServiceService) {}

  usuarioData!:Usuario

  onSubmit(infoForm:NgForm){
    this.usuarioData=infoForm.value
    this.usuarioData.nombre=this.usuario.nombre
    this.usuarioData.email=this.usuario.email
    this.usuarioService.updateUsuario(this.usuarioData).subscribe((data:any)=>{
      this.dialogRef.close()
    })
  }

  ngOnInit() {
    this.usuario = this.data['usuario'];
    this.usuarioData = this.usuario;
  }

  onNoClick(){
    this.dialogRef.close()
  }
}
