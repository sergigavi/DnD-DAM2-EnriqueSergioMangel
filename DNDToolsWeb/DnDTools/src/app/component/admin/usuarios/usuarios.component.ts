<<<<<<< HEAD
import {Component, OnInit} from '@angular/core';
=======
import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface PeriodicElement {
  nombreCuenta: string;
  correo: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {nombreCuenta: 'Pepe',correo:'pepe@gmail.com'},
  {nombreCuenta: 'MarioGLd',correo:'marioGl@gmail.com'},
  {nombreCuenta: 'PanFande',correo:'Pan@gmail.com'},
  {nombreCuenta: 'lloritos',correo:'lloritos@gmail.com'},
  {nombreCuenta: 'WolfHunter',correo:'Federico@gmail.com'},
  {nombreCuenta: 'Ferianos',correo:'ferunacos@gmail.com'},
  {nombreCuenta: 'ArcpFilicos',correo:'frediculos@gmail.com'},
  {nombreCuenta: 'prediches',correo:'ferivolisas@gmail.com'},
  {nombreCuenta: 'Irefciuas',correo:'ferodgidoso@gmail.com'},
  {nombreCuenta: 'Samuner',correo:'samonDelon@gmail.com'},
];
>>>>>>> main

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})

<<<<<<< HEAD

=======
>>>>>>> main
export class UsuariosComponent implements OnInit  {
  title = 'Usuarios';
  opened = false;

<<<<<<< HEAD
  ngOnInit(): void {
=======
  displayedColumns: string[] = ['nombreCuenta','correo','acceder','editar'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogRef = this.dialog.open(DialogUsuarios);

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
>>>>>>> main
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

<<<<<<< HEAD

}
=======
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
>>>>>>> main
