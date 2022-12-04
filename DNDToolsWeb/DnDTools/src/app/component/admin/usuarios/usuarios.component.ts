import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Usuario, UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})

export class UsuariosComponent implements OnInit  {
[x: string]: any;
  title = 'Usuarios';
  opened = false;

  //data nombrado aqui
  data2:Usuario[]=[];
  displayedColumns: string[] = ['nombreCuenta','correo','acceder','editar'];
  dataSource = new MatTableDataSource<Usuario>([])

  constructor(public dialog: MatDialog,private usuarioService: UsuarioServiceService){
    //esto hace que se envie directamente a la tabla
    this.usuarioService.getUsuarios().subscribe(x=>{
      this.data2 = x;
      console.log(this.data2);
    });
  }

  openDialog(nombre:String,correo:String) {
    const dialogRef = this.dialog.open(DialogUsuarios,{
      data:{name:nombre,mail:correo}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2(nombre:String,correo:String) {
    const dialogRef = this.dialog.open(Dialog2Usuarios,{
      data:{name:nombre,mail:correo}
    });

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

export class DialogUsuarios {
  nombre: any;
  correo: any;

  constructor(
    public dialogRef: MatDialogRef<UsuariosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UsuariosComponent) {}

    ngOnInit() {
      this.nombre = this.data['name'];
      this.correo = this.data['mail'];
    }
}

@Component({
  selector: 'usuariosDialog2',
  templateUrl: 'usuariosDialog2.html'
})

export class Dialog2Usuarios {
  nombre: any;
  correo: any;

  constructor(
    public dialogRef: MatDialogRef<UsuariosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UsuariosComponent) {}

  ngOnInit() {
    this.nombre = this.data['name'];
    this.correo = this.data['mail'];
  }
}
