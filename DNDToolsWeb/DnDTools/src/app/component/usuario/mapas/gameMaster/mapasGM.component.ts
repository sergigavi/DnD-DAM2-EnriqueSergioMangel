import {Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

export interface PeriodicElement {
  nombreUbicacion: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {nombreUbicacion: 'Doragol'},
  {nombreUbicacion: 'PuebloMaromar'},
  {nombreUbicacion: 'Castillo Orisia'},
  {nombreUbicacion: 'Mar Muerto'},
  {nombreUbicacion: 'Isla Calavera'},
  {nombreUbicacion: 'Mapa del tesero'},
  {nombreUbicacion: 'Raìdps del Ferri'},
  {nombreUbicacion: 'Fuerte Donbass'},
  {nombreUbicacion: 'Frontera Orizonea'},
  {nombreUbicacion: 'Dragon Home'},
];

@Component({
  selector: 'app-mapasGM',
  templateUrl: './mapasGM.component.html',
  styleUrls: ['./mapasGM.component.css']
})


export class MapasGMComponent implements OnInit  {
  title = 'Mapas GM';
  opened = false;

  displayedColumns: string[] = ['nombreUbicacion','acceder', 'editar'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog,private router:Router){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogMapasGM);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2() {
    const dialogRef = this.dialog.open(DialogMapasGM2);

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
  selector: 'mapasGMDialog',
  templateUrl: 'mapasGMDialog.html'
})

export class DialogMapasGM {

}

@Component({
  selector: 'mapasGMDialog2',
  templateUrl: 'mapasGMDialog2.html'
})

export class DialogMapasGM2 {

}
