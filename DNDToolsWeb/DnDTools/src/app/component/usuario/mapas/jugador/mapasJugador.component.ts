import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

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
  selector: 'app-mapasJugador',
  templateUrl: './mapasJugador.component.html',
  styleUrls: ['./mapasJugador.component.css']
})


export class MapasJugadorComponent implements OnInit   {
  title = 'Mapas Jugador';
  opened = false;

  displayedColumns: string[] = ['nombreUbicacion','acceder'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);


  constructor(public dialog: MatDialog,private router:Router, private cookieService:CookieService){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogMapasJugador);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator

  ngOnInit(): void {
    this.dataSource.paginator=this.paginator;
    if(!this.cookieService.check("CurrentUserId")){
      this.router.navigate(['/'])
    }
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator;
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

@Component({
  selector: 'mapasJugadorDialog',
  templateUrl: 'mapasJugadorDialog.html'
})

export class DialogMapasJugador {}

