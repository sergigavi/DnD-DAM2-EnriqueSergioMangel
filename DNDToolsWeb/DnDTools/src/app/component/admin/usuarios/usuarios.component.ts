import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Usuario, UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})

export class UsuariosComponent implements OnInit  {
  title = 'Usuarios';
  opened = false;

  //data nombrado aqui
  public data:Usuario[]=[];
  public data2:Usuario[]=[];
  displayedColumns: string[] = ['nombreCuenta','correo','acceder','editar'];
  dataSource = new MatTableDataSource<Usuario>([])

  constructor(public dialog: MatDialog,private usuarioService: UsuarioServiceService){
    //esto hace que se envie directamente a la tabla
    this.usuarioService.getUsuarios().subscribe(x=>{
      this.data = x;
      console.log(this.data);
    });
  }

  openDialog(usuario:Usuario) {
    const dialogRef = this.dialog.open(DialogUsuarios)

    console.log(usuario);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2() {
    const dialogRef = this.dialog.open(Dialog2Usuarios);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator

  ngOnInit(): void {
    this.dataSource.paginator=this.paginator;
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator;
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

@Component({
  selector: 'usuariosDialog',
  templateUrl: 'usuariosDialog.html'
})

export class DialogUsuarios {}

@Component({
  selector: 'usuariosDialog2',
  templateUrl: 'usuariosDialog2.html'
})

export class Dialog2Usuarios {}
