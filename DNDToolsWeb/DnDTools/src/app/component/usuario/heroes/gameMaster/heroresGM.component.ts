import {Component, OnInit, ViewChild} from '@angular/core';
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
  selector: 'app-heroesGM',
  templateUrl: './heroesGM.component.html',
  styleUrls: ['./heroesGM.component.css']
})


export class HeroesGMComponent implements OnInit  {
  title = 'Heroes GM';
  opened = false;

  displayedColumns: string[] = ['nombreHeroes','acceder','editar'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogRef = this.dialog.open(DialogHeroesGM);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2() {
    const dialogRef = this.dialog.open(Dialog2HeroesGM);

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
  selector: 'heroesGMDialog',
  templateUrl: 'heroesGMDialog.html'
})

export class DialogHeroesGM {}

@Component({
  selector: 'heroesGMDialog2',
  templateUrl: 'heroesGMDialog2.html'
})

export class Dialog2HeroesGM {}

