import {Component, OnInit, ViewChild, EventEmitter, Output} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface PeriodicElement {
  nombreHeroes: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {nombreHeroes: 'Samuraichi'},
  {nombreHeroes: 'Shadow'},
  {nombreHeroes: 'Reaper'},
  {nombreHeroes: 'Revenant'},
  {nombreHeroes: 'Cody Rivert'},
  {nombreHeroes: 'Guardiatus'},
  {nombreHeroes: 'Persa Meriquel'},
  {nombreHeroes: 'Atlas'},
  {nombreHeroes: 'Aquiles Darium'},
  {nombreHeroes: 'Dragon Slayer'},
];

@Component({
  selector: 'app-heroesJugador',
  templateUrl: './heroesJugador.component.html',
  styleUrls: ['./heroesJugador.component.css']
})


export class HeroesJugadorComponent implements OnInit  {

  message: string = "Heroes Jugador";

  @Output() messageEvent = new EventEmitter<string>();

  title = 'Heroes Jugador';
  opened = false;

  displayedColumns: string[] = ['nombreHeroes','acceder'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogRef = this.dialog.open(DialogHeroesJugador);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2() {
    const dialogRef = this.dialog.open(Dialog2HeroesJugador);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

@Component({
  selector: 'heroesJugadorDialog',
  templateUrl: 'heroesJugadorDialog.html'
})

export class DialogHeroesJugador {}

@Component({
  selector: 'heroesJugadorDialog2',
  templateUrl: 'heroesJugadorDialog2.html'
})

export class Dialog2HeroesJugador {}
